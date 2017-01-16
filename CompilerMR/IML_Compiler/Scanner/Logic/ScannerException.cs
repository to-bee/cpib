using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IML_Compiler.IMLScanner.Logic
{
    public class ScannerException : Exception
    {
        public ScannerException(string msg) : base(msg)
        {
        }
    }
}
