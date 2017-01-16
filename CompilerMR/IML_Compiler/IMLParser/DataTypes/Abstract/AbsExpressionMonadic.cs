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
    public class AbsExpressionMonadic : IAbsExpression
    {
        public MonadicOperator Operator { get; set; }
        public IAbsExpression Expression { get; set; }
        public void Check()
        {
            if (Operator == MonadicOperator.MINUS)
            {
                if (!(Expression.GetExprType() == TYPE.FLOAT || Expression.GetExprType() == TYPE.INT32 || Expression.GetExprType() == TYPE.INT64))
                    throw new CheckerException($"Minus operator not allowed with type {Expression.GetExprType()}");
            }
            else if (Operator == MonadicOperator.NOT)
            {
                if (Expression.GetExprType() != TYPE.BOOL)
                {
                    throw new CheckerException($"only bool values allowed with bool operator");
                }
            }
            else
            {
                throw new CheckerException("unexpected monadic operator");
            }
        }

        public int Code(int loc)
        {
            SymbolTable.Deref = true;
            var loc1 = Expression.Code(loc);
            SymbolTable.Deref = false;

            if (Operator == MonadicOperator.MINUS)
            {
                switch (Expression.GetExprType())
                {
                    case TYPE.FLOAT:
                        CodeGenerationContext.Instructions[loc1] = new NegFloat();
                        break;
                    case TYPE.INT32:
                        CodeGenerationContext.Instructions[loc1] = new NegInt();
                        break;
                    case TYPE.INT64:
                        CodeGenerationContext.Instructions[loc1] = new NegLong();
                        break;
                };
                return loc1 + 1;
            } else if (Operator == MonadicOperator.NOT)
            {
                CodeGenerationContext.Instructions[loc1] = new LoadImInt() {Value = -1};
                CodeGenerationContext.Instructions[loc1 + 1] = new MultInt();
                return loc1 + 2;
            }
            return loc1;
        }

        public TYPE GetExprType()
        {
            if (Operator == MonadicOperator.NOT) return TYPE.BOOL;
            else if (Operator == MonadicOperator.MINUS) return Expression.GetExprType();
            else throw new CheckerException("unexpected monadic operator");
        }

        public bool IsLValue()
        {
            return false;
        }
    }
}
