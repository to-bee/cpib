using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IML_Compiler.IMLParser.DataTypes.Abstract
{
    public class AbsSkipCmd : IAbsCmd
    {
        public void Check()
        {
            // yay, skip command, no checks
        }

        public int Code(int loc)
        {
            return loc;
        }
    }
}
