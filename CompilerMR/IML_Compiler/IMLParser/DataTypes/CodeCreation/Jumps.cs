using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Principal;
using System.Text;
using System.Threading.Tasks;

namespace IML_Compiler.IMLParser.DataTypes.CodeCreation
{
    public class UncondJump : AbsInstruction
    {
        public int Address { get; set; }
        public override string Export()
        {
            return base.Export() + " " + Address;
        }
    }

    public class CondJump : AbsInstruction
    {
        public int Address { get; set; }
        public override string Export()
        {
            return base.Export() + " " + Address;
        }
    }

    public class PrepareRoutJump : AbsInstruction
    {
        public string FuncIdent { get; set; }
        public override string Export()
        {
            return base.Export() + " "+ FuncIdent;
        }
    }
}
