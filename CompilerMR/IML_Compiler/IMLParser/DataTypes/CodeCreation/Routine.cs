using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IML_Compiler.IMLParser.DataTypes.CodeCreation
{
    public class AllocBlock : AbsInstruction
    {
        public int Size { get; set; }
        public override string Export()
        {
            return base.Export() + " " + Size;
        }

    }

    public class AllocStack : AbsInstruction
    {
        public int MaxSize { get; set; }
        public override string Export()
        {
            return base.Export() + " " + MaxSize;
        }

    }

    public class Call : AbsInstruction
    {
        public int RoutAddress { get; set; }
        public override string Export()
        {
            return base.Export() + " " + RoutAddress;
        }

    }

    public class Return : AbsInstruction
    {
        public int Size { get; set; }
        public override string Export()
        {
            return base.Export() + " " + Size;
        }
    }
}
