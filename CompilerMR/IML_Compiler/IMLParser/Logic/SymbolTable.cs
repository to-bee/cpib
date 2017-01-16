using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.NetworkInformation;
using IML_Compiler.IMLParser.DataTypes.Abstract;
using IML_Compiler.IMLParser.Logic;
using IML_Compiler.IMLScanner.DataTypes;

namespace IML_Compiler.IMLParser.DataTypes.CodeCreation
{
    public class SymbolTable
    {
        public enum Scope
        {
            Local, Global
        }

        public enum Access
        {
            Direct, Indirect
        }

        public class Entry
        {
            public string Name { get; set; }
            public Scope Scope { get; set; }
            public int Address { get; set; }
            public Access Access { get; set; }
            public TYPE Type { get; set; }
        }

        private class EntryComparer : IComparer<Entry>
        {
            public int Compare(Entry x, Entry y)
            {
                if (x.Scope == y.Scope)
                    return 0;
                if (x.Scope == Scope.Local)
                    return -1;
                return 1;
            }
        }

        private static int globalPointer = 0;
        
        internal static Entry GetEntry(string ident)
        {
            return Table.OrderBy(e => e, new EntryComparer()).First(e => e.Name == ident);
        }

        public static void ClearLocals()
        {
            Table.RemoveAll(e => e.Scope == Scope.Local);
            CurrentLocalPointer = 3;
            //localPointer = 3;
        }

        public static void AddEntry(Entry e)
        {
            e.Scope = CurrentScope;
            Table.Add(e);
        }

        public static void SetAddress(Entry e)
        {
            if (e.Scope == Scope.Global)
            {
                e.Address = globalPointer;
                globalPointer++;
            }
            else
            {
                e.Address = CurrentLocalPointer;
                CurrentLocalPointer++;
            }
        }

        public static List<Entry> Table = new List<Entry>();
        public static Scope CurrentScope { get; set; } = Scope.Global;

        public static bool Deref { get; set; } = false;

        public static int CurrentLocalPointer { get; set; } = 3;

        public static Dictionary<string, int> RoutineSymbols = new Dictionary<string, int>();

        public static List<AbsFunctionDeclaration> FuncDecls = new List<AbsFunctionDeclaration>();
        public static List<AbsProcedureDeclaration> ProcDecls = new List<AbsProcedureDeclaration>();

        public static void FixRoutineJumps()
        {
            for (int i = 0; i < CodeGenerationContext.Instructions.Length; i++)
            {
                if (CodeGenerationContext.Instructions[i] is PrepareRoutJump)
                {
                    var ident = (CodeGenerationContext.Instructions[i] as PrepareRoutJump).FuncIdent;
                    CodeGenerationContext.Instructions[i] = new Call() { RoutAddress = RoutineSymbols[ident]};
                }
            }
        }

        public static int LoadRoutineParametersIntoContext(int loc, string ident, List<AbsParameter> parameters, out int returnSize, out int relAddr)
        {
            var myIdent = ident + "$";
            
            foreach (var param in parameters)
            {
                myIdent += param.StorageDeclaration.TypeDeclaration + "$";
            }
            SymbolTable.RoutineSymbols.Add(myIdent, loc);

            SymbolTable.CurrentScope = SymbolTable.Scope.Local;

            relAddr = -1;
            returnSize = 0;

            foreach (var p in parameters.Where(p => p.MechMode == MECHMODE.REF).Reverse())
            {
                var e = new SymbolTable.Entry()
                {
                    Access = SymbolTable.Access.Indirect,
                    Address = relAddr,
                    Name = p.StorageDeclaration.Ident,
                    Scope = SymbolTable.Scope.Local,
                    Type = p.StorageDeclaration.TypeDeclaration
                };
                SymbolTable.Table.Add(e);
                relAddr--;
                returnSize++;
            }

            foreach (var p in parameters.Where(p => p.MechMode == MECHMODE.COPY && p.FlowMode == FLOWMODE.IN).Reverse())
            {
                var e = new SymbolTable.Entry()
                {
                    Access = SymbolTable.Access.Direct,
                    Address = relAddr,
                    Name = p.StorageDeclaration.Ident,
                    Scope = SymbolTable.Scope.Local,
                    Type = p.StorageDeclaration.TypeDeclaration
                };
                SymbolTable.Table.Add(e);
                relAddr--;
                returnSize++;
            }

            foreach (var p in parameters.Where(p => p.MechMode == MECHMODE.COPY && p.FlowMode == FLOWMODE.INOUT).Reverse())
            {
                var e = new SymbolTable.Entry()
                {
                    Access = SymbolTable.Access.Direct,
                    Address = relAddr,
                    Name = p.StorageDeclaration.Ident,
                    Scope = SymbolTable.Scope.Local,
                    Type = p.StorageDeclaration.TypeDeclaration
                };
                SymbolTable.Table.Add(e);
                relAddr -= 2;
            }

            foreach (var p in parameters.Where(p => p.MechMode == MECHMODE.COPY && p.FlowMode == FLOWMODE.OUT).Reverse())
            {
                var e = new SymbolTable.Entry()
                {
                    Access = SymbolTable.Access.Direct,
                    Address = relAddr,
                    Name = p.StorageDeclaration.Ident,
                    Scope = SymbolTable.Scope.Local,
                    Type = p.StorageDeclaration.TypeDeclaration
                };
                SymbolTable.Table.Add(e);
                relAddr -= 2;
            }

            return loc;
        }

        public static int LoadRoutineParametersToStack(int loc, List<IAbsExpression> myParams, List<AbsParameter> routParams,
            string routName, out int stores)
        {
            var outParams = new List<IAbsExpression>();
            var inOutParams = new List<IAbsExpression>();
            var inParams = new List<IAbsExpression>();
            var refParams = new List<IAbsExpression>();

            //var routParams = pDecl.ParameterList.ToList();

            for (int i = 0; i < myParams.Count; i++)
            {
                if (routParams[i].MechMode == MECHMODE.REF)
                {
                    refParams.Add(myParams[i]);
                }
                else
                {
                    if (routParams[i].FlowMode == FLOWMODE.IN)
                    {
                        inParams.Add(myParams[i]);
                    }
                    else if (routParams[i].FlowMode == FLOWMODE.INOUT)
                    {
                        inOutParams.Add(myParams[i]);
                    }
                    else
                    {
                        outParams.Add(myParams[i]);
                    }
                }
            }

            foreach (var outP in outParams)
            {
                if (!outP.IsLValue())
                {
                    throw new CheckerException($"Out parameter is not a lvalue");
                }
                loc = outP.Code(loc);
                CodeGenerationContext.Instructions[loc] = new AllocBlock() { Size = 1 };
                loc++;
            }

            foreach (var inOutP in inOutParams)
            {
                if (!inOutP.IsLValue())
                {
                    throw new CheckerException($"Inout parameter is not a lvalue");
                }
                loc = inOutP.Code(loc);
                CodeGenerationContext.Instructions[loc] = new Dup();
                loc++;
                CodeGenerationContext.Instructions[loc] = new Deref();
                loc++;
            }

            SymbolTable.Deref = true;
            foreach (var inP in inParams)
            {
                loc = inP.Code(loc);
            }
            SymbolTable.Deref = false;

            foreach (var refP in refParams)
            {
                if (!refP.IsLValue())
                {
                    throw new CheckerException($"Inout parameter is not a lvalue");
                }
                loc = refP.Code(loc);
            }

            var procIdent = routName + "$";
            foreach (var p in routParams)
            {
                procIdent += p.StorageDeclaration.TypeDeclaration + "$";
            }

            CodeGenerationContext.Instructions[loc] = new PrepareRoutJump() { FuncIdent = procIdent };

            loc++;

            stores = outParams.Count + inOutParams.Count;

            return loc;
        }
    }
}
