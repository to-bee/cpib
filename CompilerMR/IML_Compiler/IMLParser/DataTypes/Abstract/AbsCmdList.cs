using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IML_Compiler.IMLParser.DataTypes.Abstract
{
    public class AbsCmdList : IAbsList<IAbsCmd>
    {
        public IAbsCmd Cmd { get; set; }
        public IAbsList<IAbsCmd> CmdList { get; set; }

        public List<IAbsCmd> ToList()
        {
            var list = new List<IAbsCmd>();
            var currList = this;

            while (currList != null)
            {
                if (currList.Cmd != null)
                    list.Add(currList.Cmd);
                currList = currList.CmdList as AbsCmdList;
            }

            return list;
        }

        public void Check()
        {
            Cmd.Check();
            CmdList.Check();
        }

        public int Code(int loc)
        {
            foreach (var cmd in ToList())
            {
                loc = cmd.Code(loc);
            }
            return loc;
        }
    }
}
