using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IML_Compiler.IMLParser.DataTypes.Abstract
{
    public class AbsExpressionList : IAbsList<IAbsExpression>
    {
        public IAbsExpression Expression { get; set; }
        public IAbsList<IAbsExpression> ExpressionList { get; set; }
        public List<IAbsExpression> ToList()
        {
            var list = new List<IAbsExpression>();
            var currList = this;

            while (currList != null)
            {
                if (currList.Expression != null)
                    list.Add(currList.Expression);
                currList = currList.ExpressionList as AbsExpressionList;
            }

            return list;
        }

        public void Check()
        {
            Expression?.Check();
            ExpressionList.Check();
        }

        public int Code(int loc)
        {
            foreach (var e in ToList())
            {
                loc = e.Code(loc);
            }
            return loc;
        }
    }
}
