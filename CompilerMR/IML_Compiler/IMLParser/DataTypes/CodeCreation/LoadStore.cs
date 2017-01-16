using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IML_Compiler.IMLParser.DataTypes.CodeCreation
{
    public class LoadImInt : AbsInstruction
    {
        public int Value { get; set; }
        public override string Export()
        {
            return base.Export() + " " + Value;
        }
    }

    public class LoadImFloat : AbsInstruction 
    {
        public float Value { get; set; }
        public override string Export()
        {
            var val = Value.ToString();

            if (float.IsPositiveInfinity(Value))
                val = "POSITIVE_INFINITY";
            if (float.IsNegativeInfinity(Value))
                val = "NEGATIVE_INFINITY";
            if (float.IsNaN(Value))
                val = "NaN";

            return base.Export() + " " + val;
        }
    }

    public class LoadImLong : AbsInstruction
    {
        public long Value { get; set; }
        public override string Export()
        {
            return base.Export() + " " + Value;
        }
    }

    public class LoadAddrRel : AbsInstruction
    {
        public int RelAddress { get; set; }
        public override string Export()
        {
            return base.Export() + " " + RelAddress;
        }
    }

    public class Deref : AbsInstruction
    {
    }

    public class Store : AbsInstruction
    {
    }

    public class Dup : AbsInstruction
    {
    }
}
