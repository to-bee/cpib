using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IML_Compiler.IMLParser.DataTypes
{
    public class CheckerException : Exception
    {
        public CheckerException(string message)
            : base(message)
        {
            
        }
    }
}
