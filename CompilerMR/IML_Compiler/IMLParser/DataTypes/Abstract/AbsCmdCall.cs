using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http.Headers;
using IML_Compiler.IMLParser.DataTypes.CodeCreation;
using IML_Compiler.IMLParser.DataTypes.Concrete;
using IML_Compiler.IMLParser.Logic;
using IML_Compiler.IMLScanner.DataTypes;

namespace IML_Compiler.IMLParser.DataTypes.Abstract
{
    public class AbsCmdCall : IAbsCmd
    {
        public IAbsList<IAbsExpression> ExpressionList { get; set; }
        public IAbsList<string> GlobalInits { get; set; }
        public string Ident { get; set; }
        public void Check()
        {

            foreach (var v in GlobalInits.ToList())
            {
                if (GlobalContext.Context.GetVariable(v).Initialized)
                    throw new CheckerException($"global variable {v} already initialized");

                GlobalContext.Context.GetVariable(v).Initialized = true;
            }

            ExpressionList.Check();
            GlobalInits.Check();

            FindMatchingRoutine.ExistMatchingRoutine(this);

            //var procs = GlobalContext.Context.RoutineContexts.Where(r => r.Name == Ident).ToList();
            //if (procs.Count == 0)
            //    throw new CheckerException($"no procedure named {Ident} found");

            //if (procs[0] is FunctionContext)
            //    throw new CheckerException($"{Ident} is a function, not a procedure");

            //bool foundMatchingParamsList = false;

            //var exprList = ExpressionList.ToList();

            //foreach(var r in procs)
            //{
            //    var p = (ProcContext) r;

            //    if (p.Parameters.Count == exprList.Count)
            //    {
            //        bool cancel = false;
            //        int i = 0;
            //        foreach (var expr in exprList)
            //        {
            //            if (p.Parameters[i].Type != expr.GetExprType())
            //            {
            //                cancel = true;
            //                break;
            //            }
            //            i++;
            //        }
            //        if (!cancel)
            //        {
            //            foundMatchingParamsList = true;
            //            break;
            //        }
            //    }
            //}

            //if (!foundMatchingParamsList)
            //    throw new CheckerException($"couldn't find a procedure named {Ident} with a matching signature");
        }

        public int Code(int loc)
        {
            foreach (var inits in GlobalInits.ToList())
            {
                //loc = new AbsExpressionStore() {Ident = inits}.Code(loc);
            }

            //out, inout, in, ref
            AbsProcedureDeclaration pDecl = null;
            var myParams = ExpressionList.ToList();
            foreach (var p in SymbolTable.ProcDecls.Where(p => p.Ident == Ident))
            {
                var procParams = p.ParameterList.ToList();
                if (procParams.Count == myParams.Count)
                {
                    bool match = true;
                    for (int i = 0; i < procParams.Count; i++)
                    {
                        var aType = procParams[i].StorageDeclaration.TypeDeclaration;
                        var bType = myParams[i].GetExprType();
                        if (aType != bType)
                            match = false;
                    }
                    if (match)
                    {
                        pDecl = p;
                        break;
                    }
                }
            }

            //var outParams = new List<IAbsExpression>();
            //var inOutParams = new List<IAbsExpression>();
            //var inParams = new List<IAbsExpression>();
            //var refParams = new List<IAbsExpression>();

            //var routParams = pDecl.ParameterList.ToList();

            //for (int i = 0; i < myParams.Count; i++)
            //{
            //    if (routParams[i].MechMode == MECHMODE.REF)
            //    {
            //        refParams.Add(myParams[i]);
            //    }
            //    else
            //    {
            //        if (routParams[i].FlowMode == FLOWMODE.IN)
            //        {
            //            inParams.Add(myParams[i]);
            //        }
            //        else if (routParams[i].FlowMode == FLOWMODE.INOUT)
            //        {
            //            inOutParams.Add(myParams[i]);
            //        }
            //        else
            //        {
            //            outParams.Add(myParams[i]);
            //        }
            //    }
            //}

            //foreach (var outP in outParams)
            //{
            //    loc = outP.Code(loc);
            //    CodeGenerationContext.Instructions[loc] = new AllocBlock() {Size = 1};
            //    loc++;
            //}

            //foreach (var inOutP in inOutParams)
            //{
            //    loc = inOutP.Code(loc);
            //    CodeGenerationContext.Instructions[loc] = new Dup();
            //    loc++;
            //    CodeGenerationContext.Instructions[loc] = new Deref();
            //    loc++;
            //}

            //SymbolTable.Deref = true;
            //foreach (var inP in inParams)
            //{
            //    loc = inP.Code(loc);
            //}
            //SymbolTable.Deref = false;

            //foreach (var refP in refParams)
            //{
            //    loc = refP.Code(loc);
            //}

            //var procIdent = pDecl.Ident + "_";
            //foreach (var p in routParams)
            //{
            //    procIdent += p.StorageDeclaration.TypeDeclaration + "_";
            //}

            //CodeGenerationContext.Instructions[loc] = new PrepareRoutJump() {FuncIdent = procIdent};

            //loc++;

            int stores;
            loc = SymbolTable.LoadRoutineParametersToStack(loc, myParams, pDecl.ParameterList.ToList(), pDecl.Ident, out stores);

            for (int i = 0; i < stores; i++)
            {
                CodeGenerationContext.Instructions[loc] = new Store();
                loc++;
            }

            return loc;
        }
    }
}
