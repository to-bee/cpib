using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IML_Compiler.IMLParser.DataTypes.Abstract
{
    // For optional global inits bei procs/(funcs?)
    public class AbsExpressionStoreList : IAbsList<AbsExpressionStore>
    {
        public AbsExpressionStore ExpressionStore { get; set; }
        public IAbsList<AbsExpressionStore> ExpressionStoreList { get; set; }

        public List<AbsExpressionStore> ToList()
        {
            var list = new List<AbsExpressionStore>();
            var curr = this;

            while (curr != null)
            {
                if (curr.ExpressionStore != null) 
                    list.Add(curr.ExpressionStore);

                curr = ExpressionStoreList as AbsExpressionStoreList;
            }

            return list;
        }

        public void Check()
        {
            ExpressionStore?.Check();
            ExpressionStoreList.Check();
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
