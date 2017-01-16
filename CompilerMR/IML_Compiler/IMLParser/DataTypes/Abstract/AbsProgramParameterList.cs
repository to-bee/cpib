using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IML_Compiler.IMLParser.DataTypes.Abstract
{
    public class AbsProgramParameterList : IAbsList<AbsProgramParameter>
    {
        public AbsProgramParameter ProgramParameter { get; set; }
        public IAbsList<AbsProgramParameter> ProgramParameterList { get; set; }

        public List<AbsProgramParameter> ToList()
        {
            var list = new List<AbsProgramParameter>();
            var currList = this;

            while (currList != null)
            {
                if (currList.ProgramParameter != null)
                    list.Add(currList.ProgramParameter);
                currList = currList.ProgramParameterList as AbsProgramParameterList;
            }

            return list;
        }

        public void Check()
        {
            ProgramParameter?.Check();
            ProgramParameterList.Check();
        }

        public int Code(int loc)
        {
            throw new NotSupportedException("Use methods CodeIn(int loc) or CodeOut(int loc) instead");
        }

        public int CodeIn(int loc)
        {
            foreach (var p in ToList())
            {
                loc = p.CodeIn(loc);
            }
            return loc;
        }

        public int CodeOut(int loc)
        {
            foreach (var p in ToList())
            {
                loc = p.CodeOut(loc);
            }
            return loc;
        }
    }
}
