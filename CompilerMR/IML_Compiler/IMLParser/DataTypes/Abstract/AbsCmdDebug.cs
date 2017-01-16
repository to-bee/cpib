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
    public class AbsCmdDebug : IAbsCmd
    {
        public IAbsExpression Expression { get; set; }
        public bool In { get; set; }
        public void Check()
        {
            if (In)
            {
                if (!(Expression is AbsExpressionStore || Expression is AbsExpressionIdent))
                {
                    throw new CheckerException("Call to DebugIn must have a lvalue to assign the value to");
                }
            }
            Expression.Check();
        }

        public int Code(int loc)
        {
            var t = Expression.GetExprType();
            int loc2 = -1;

            string indicator = null;
            if (Expression is AbsExpressionStore)
            {
                indicator = (Expression as AbsExpressionStore).Ident;
            }
            else if (Expression is AbsExpressionIdent)
            {
                indicator = (Expression as AbsExpressionIdent).Ident;
            }
            else
            {
                indicator = "Ausgabe: ";
            }

            if (In)
            {
                var loc1 = Expression.Code(loc);
                switch (t)
                {
                    case TYPE.BOOL:
                        CodeGenerationContext.Instructions[loc1] = new InputBool() { Indicator = indicator };
                        break;
                    case TYPE.FLOAT:
                        CodeGenerationContext.Instructions[loc1] = new InputFloat() { Indicator = indicator };
                        break;
                    case TYPE.INT32:
                        CodeGenerationContext.Instructions[loc1] = new InputInt() { Indicator = indicator };
                        break;
                    case TYPE.INT64:
                        CodeGenerationContext.Instructions[loc1] = new InputLong() { Indicator = indicator };
                        break;
                }
                loc2 = loc1 + 1;
            }
            else
            {
                SymbolTable.Deref = true;
                var loc1 = Expression.Code(loc);
                SymbolTable.Deref = false;
                switch (t)
                {
                    case TYPE.BOOL:
                        CodeGenerationContext.Instructions[loc1] = new OutputBool() { Indicator = indicator };
                        break;
                    case TYPE.FLOAT:
                        CodeGenerationContext.Instructions[loc1] = new OutputFloat() { Indicator = indicator };
                        break;
                    case TYPE.INT32:
                        CodeGenerationContext.Instructions[loc1] = new OutputInt() { Indicator = indicator };
                        break;
                    case TYPE.INT64:
                        CodeGenerationContext.Instructions[loc1] = new OutputLong() { Indicator = indicator };
                        break;
                }
                loc2 = loc1 + 1;
            }
            return loc2;
        }
    }
}
