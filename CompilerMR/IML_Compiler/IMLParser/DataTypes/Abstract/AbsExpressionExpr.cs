using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using IML_Compiler.IMLScanner.DataTypes;

namespace IML_Compiler.IMLParser.DataTypes.Abstract
{
    public class AbsExpressionExpr : IAbsExpression
    {
        public IAbsExpression Expression { get; set; }
        public void Check()
        {
            Expression.Check();
        }

        public int Code(int loc)
        {
            return Expression.Code(loc);
        }

        public TYPE GetExprType()
        {
            return Expression.GetExprType();
        }

        public bool IsLValue()
        {
            return Expression.IsLValue();
        }
    }
}
