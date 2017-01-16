using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IML_Compiler.IMLParser.DataTypes.Abstract
{
    public class AbsParameterList : IAbsList<AbsParameter>
    {
        public AbsParameter Parameter { get; set; }
        public IAbsList<AbsParameter> ParameterList { get; set; }
        

        public List<AbsParameter> ToList()
        {
            var list = new List<AbsParameter>();
            var currList = this;

            while (currList != null)
            {
                if (currList.Parameter != null)
                    list.Add(currList.Parameter);
                currList = currList.ParameterList as AbsParameterList;
            }

            return list;
        }

        public void Check()
        {
            Parameter?.Check();
            ParameterList.Check();
        }

        public int Code(int loc)
        {
            foreach (var p in ToList())
            {
                loc = p.Code(loc);
            }
            return loc;
        }
    }
}
