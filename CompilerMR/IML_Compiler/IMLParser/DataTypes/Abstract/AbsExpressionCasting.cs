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
    public class AbsExpressionCasting : IAbsExpression
    {
        public TYPE CastingToType { get; set; }
        public IAbsExpression Expression { get; set; }
        public void Check()
        {
            Expression.Check();

            if (CastingToType == TYPE.BOOL || Expression.GetExprType() == TYPE.BOOL)
                throw new CheckerException("only casting of numbers is supported...");
        }

        public int Code(int loc)
        {
            loc = Expression.Code(loc);
            if (CastingToType != Expression.GetExprType())
            {
                switch (CastingToType)
                {
                    case TYPE.FLOAT:
                        switch (Expression.GetExprType())
                        {
                            case TYPE.INT32:
                                CodeGenerationContext.Instructions[loc] = new CastIntToFloat();
                                break;
                            case TYPE.INT64:
                                CodeGenerationContext.Instructions[loc] = new CastLongToFloat();
                                break;
                        }
                        break;
                    case TYPE.INT64:
                        switch (Expression.GetExprType())
                        {
                            case TYPE.INT32:
                                CodeGenerationContext.Instructions[loc] = new CastIntToLong();
                                break;
                            case TYPE.FLOAT:
                                CodeGenerationContext.Instructions[loc] = new CastFloatToLong();
                                break;
                        }
                        break;
                    case TYPE.INT32:
                        switch (Expression.GetExprType())
                        {
                            case TYPE.FLOAT:
                                CodeGenerationContext.Instructions[loc] = new CastFloatToInt();
                                break;
                            case TYPE.INT64:
                                CodeGenerationContext.Instructions[loc] = new CastLongToInt();
                                break;
                        }
                        break;
                }
                loc++;
            }
            return loc;
        }

        public TYPE GetExprType()
        {
            return CastingToType;
        }

        public bool IsLValue()
        {
            return false;
        }
    }
}
