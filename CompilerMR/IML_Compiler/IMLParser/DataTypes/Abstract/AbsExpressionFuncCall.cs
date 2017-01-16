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
    public class AbsExpressionFuncCall : IAbsExpression
    {
        public string Ident { get; set; }
        public IAbsList<IAbsExpression> Parameter { get; set; }
        public void Check()
        {
            Parameter.Check();

            FindMatchingRoutine.ExistMatchingRoutine(this);
        }

        public int Code(int loc)
        {
            AbsFunctionDeclaration fDecl = null;
            var myParams = Parameter.ToList();
            foreach (var f in SymbolTable.FuncDecls.Where(f => f.Ident == Ident))
            {
                var funcParams = f.ParameterList.ToList();
                if (funcParams.Count == myParams.Count)
                {
                    var match = true;
                    for (int i = 0; i < funcParams.Count; i++)
                    {
                        var aType = funcParams[i].StorageDeclaration.TypeDeclaration;
                        var bType = myParams[i].GetExprType();
                        if (aType != bType)
                            match = false;
                    }
                    if (match)
                    {
                        fDecl = f;
                        break;
                    }
                }
            }
            CodeGenerationContext.Instructions[loc] = new AllocBlock() { Size = 1 };
            loc++;

            int stores;
            loc = SymbolTable.LoadRoutineParametersToStack(loc, myParams, fDecl.ParameterList.ToList(), fDecl.Ident,
                out stores);

            for (int i = 0; i < stores; i++)
            {
                CodeGenerationContext.Instructions[loc] = new Store();
                loc++;
            }

            return loc;
        }

        public TYPE GetExprType()
        {
            var func = GlobalContext.Context.GetRoutines<FunctionContext>(Ident)[0];
            return func.ReturnType;
        }

        public bool IsLValue()
        {
            return false;
        }
    }
}
