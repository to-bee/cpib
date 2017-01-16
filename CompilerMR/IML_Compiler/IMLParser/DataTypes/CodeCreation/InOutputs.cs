using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.AccessControl;
using System.Text;
using System.Threading.Tasks;

namespace IML_Compiler.IMLParser.DataTypes.CodeCreation
{
    public class InputBool : AbsInstruction
    {
        public string Indicator { get; set; }
        public override string Export()
        {
            return base.Export() + " " + Indicator;
        }
    }

    public class InputInt : AbsInstruction
    {
        public string Indicator { get; set; }
        public override string Export()
        {
            return base.Export() + " " + Indicator;
        }
    }

    public class InputFloat : AbsInstruction
    {
        public string Indicator { get; set; }
        public override string Export()
        {
            return base.Export() + " " + Indicator;
        }
    }
    
    public class InputLong : AbsInstruction
    {
        public string Indicator { get; set; }
        public override string Export()
        {
            return base.Export() + " " + Indicator;
        }
    }

    public class OutputBool : AbsInstruction
    {
        public string Indicator { get; set; }
        public override string Export()
        {
            return base.Export() + " " + Indicator;
        }
    }

    public class OutputInt : AbsInstruction
    {
        public string Indicator { get; set; }
        public override string Export()
        {
            return base.Export() + " " + Indicator;
        }
    }

    public class OutputFloat : AbsInstruction
    {
        public string Indicator { get; set; }
        public override string Export()
        {
            return base.Export() + " " + Indicator;
        }
    }

    public class OutputLong : AbsInstruction
    {
        public string Indicator { get; set; }
        public override string Export()
        {
            return base.Export() + " " + Indicator;
        }
    }

    public class ProgParameterInt : AbsInstruction
    {
    }

    public class ProgParameterLong : AbsInstruction
    {
    }

    public class ProgParameterFloat : AbsInstruction
    {
    }

    public class ProgParameterBool : AbsInstruction
    {
    }
}
