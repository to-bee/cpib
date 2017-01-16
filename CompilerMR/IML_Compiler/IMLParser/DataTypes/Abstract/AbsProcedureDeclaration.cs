using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using IML_Compiler.IMLParser.DataTypes.CodeCreation;
using IML_Compiler.IMLParser.Logic;
using IML_Compiler.IMLScanner.DataTypes;

namespace IML_Compiler.IMLParser.DataTypes.Abstract
{
    public class AbsProcedureDeclaration : IAbsDeclaration
    {
        public string Ident { get; set; }
        public IAbsList<AbsParameter> ParameterList { get; set; }
        public IAbsList<AbsGlobalImport> GlobalImports { get; set; }
        public IAbsList<AbsStorageDeclaration> LocalStorageDeclarations { get; set; }
        public IAbsList<IAbsCmd> BlockCmd { get; set; }

        private ProcContext myCheckContext;

        public void Check()
        {
            var pCtx = new ProcContext(Ident);
            var oldCtx = GlobalContext.CurrentContext;
            GlobalContext.CurrentContext = pCtx;

            ParameterList.Check();
            GlobalImports.Check();
            LocalStorageDeclarations.Check();

            GlobalContext.Context.AddRoutineContext(pCtx);
            GlobalContext.CurrentContext = oldCtx;

            myCheckContext = pCtx;
        }

        public int Code(int loc)
        {
            //var myIdent = Ident + "_";
            //var myParams = ParameterList.ToList();
            //foreach (var param in myParams)
            //{
            //    myIdent += param.StorageDeclaration.TypeDeclaration + "_";
            //}
            //SymbolTable.RoutineSymbols.Add(myIdent, loc);

            SymbolTable.CurrentScope = SymbolTable.Scope.Local;

            var oldCtx = GlobalContext.CurrentContext;
            GlobalContext.CurrentContext = myCheckContext;

            //int relAddr = -1;
            //int returnSize = 0;

            //foreach (var p in myParams.Where(p => p.MechMode == MECHMODE.REF))
            //{
            //    var e = new SymbolTable.Entry()
            //    {
            //        Access = SymbolTable.Access.Indirect,
            //        Address = relAddr,
            //        Name = p.StorageDeclaration.Ident,
            //        Scope = SymbolTable.Scope.Local,
            //        Type = p.StorageDeclaration.TypeDeclaration
            //    };
            //    SymbolTable.Table.Add(e);
            //    relAddr--;
            //    returnSize++;
            //}

            //foreach (var p in myParams.Where(p => p.MechMode == MECHMODE.COPY && p.FlowMode == FLOWMODE.IN))
            //{
            //    var e = new SymbolTable.Entry()
            //    {
            //        Access = SymbolTable.Access.Direct,
            //        Address = relAddr,
            //        Name = p.StorageDeclaration.Ident,
            //        Scope = SymbolTable.Scope.Local,
            //        Type = p.StorageDeclaration.TypeDeclaration
            //    };
            //    SymbolTable.Table.Add(e);
            //    relAddr--;
            //    returnSize++;
            //}

            //foreach (var p in myParams.Where(p => p.MechMode == MECHMODE.COPY && p.FlowMode == FLOWMODE.INOUT))
            //{
            //    var e = new SymbolTable.Entry()
            //    {
            //        Access = SymbolTable.Access.Direct,
            //        Address = relAddr,
            //        Name = p.StorageDeclaration.Ident,
            //        Scope = SymbolTable.Scope.Local,
            //        Type = p.StorageDeclaration.TypeDeclaration
            //    };
            //    SymbolTable.Table.Add(e);
            //    relAddr -= 2;
            //}

            //foreach (var p in myParams.Where(p => p.MechMode == MECHMODE.COPY && p.FlowMode == FLOWMODE.OUT))
            //{
            //    var e = new SymbolTable.Entry()
            //    {
            //        Access = SymbolTable.Access.Direct,
            //        Address = relAddr,
            //        Name = p.StorageDeclaration.Ident,
            //        Scope = SymbolTable.Scope.Local,
            //        Type = p.StorageDeclaration.TypeDeclaration
            //    };
            //    SymbolTable.Table.Add(e);
            //    relAddr -= 2;
            //}

            int relAddr;
            int returnSize;

            loc = SymbolTable.LoadRoutineParametersIntoContext(loc, Ident, ParameterList.ToList(), out returnSize, out relAddr);

            loc = GlobalImports.Code(loc);
            loc = LocalStorageDeclarations.Code(loc);
            loc = BlockCmd.Code(loc);

            CodeGenerationContext.Instructions[loc] = new Return() {Size = returnSize};
            loc++;

            GlobalContext.CurrentContext = oldCtx;
            SymbolTable.ClearLocals();

            SymbolTable.CurrentScope = SymbolTable.Scope.Global;

            return loc;
        }

        public void CheckAfterDeclaration()
        {
            var oldCtx = GlobalContext.CurrentContext;
            GlobalContext.CurrentContext = myCheckContext;

            BlockCmd.Check();

            GlobalContext.CurrentContext = oldCtx;
        }
    }
}
