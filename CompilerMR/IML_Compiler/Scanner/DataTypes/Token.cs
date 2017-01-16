using IML_Compiler.IMLScanner.Logic;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IML_Compiler.IMLScanner.DataTypes
{
    /// <summary>
    /// Class for terminals. This class contains all properties and options a terminal can contain.
    /// </summary>
    public class Token
    {
        public Terminals Terminal { get; private set; }

        public Token(Terminals t)
        {
            Terminal = t;
        }

        public override string ToString()
        {
            return Terminal.ToString();
        }
    }

    public class Literal : Token
    {
        public object value { get; private set; }
        public TYPE literalType { get; set; }

        public Literal(TYPE literalType, object value) : base(Terminals.LITERAL)
        {
            this.value = value;
            this.literalType = literalType;
        }

        public override string ToString()
        {
            switch (literalType)
            {
                case TYPE.BOOL:
                    return $"({base.ToString()}_BOOL,{(bool)value})";
                case TYPE.FLOAT:
                    string s = GetFloatAsString();
                    return $"({base.ToString()}_FLOAT,{s})";
                case TYPE.INT32:
                    return $"({base.ToString()}_INT32,{(int) value})";
                case TYPE.INT64:
                    return $"({base.ToString()}_INT64,{(long)value})";
                default:
                    throw new ScannerException("Literaltype not defined");
            }
        }

        private string GetFloatAsString()
        {
            string s = "";
            float f = (float)value;

            if (float.IsPositiveInfinity(f))
            {
                s = "POSITIVE_INFINITY";
            }
            else if (float.IsNegativeInfinity(f))
            {
                s = "NEGATIVE_INFINITY";
            }
            else if (float.IsNaN(f))
            {
                s = "NaN";
            }
            else
            {
                s = value.ToString();
            }

            return s;
        }
    }

    public class RelOpr : Token
    {
        public RELOPR value { get; private set; }

        public RelOpr(RELOPR value) : base(Terminals.RELOPR)
        {
            this.value = value;
        }

        public override string ToString()
        {
            return $"({base.ToString()},{value})";
        }
    }

    public class MultOpr : Token
    {
        public MULTOPR value { get; private set; }

        public MultOpr(MULTOPR value) : base(Terminals.MULTOPR)
        {
            this.value = value;
        }

        public override string ToString()
        {
            return $"({base.ToString()},{value})";
        }
    }

    public class AddOpr : Token
    {
        public ADDOPR value { get; private set; }

        public AddOpr(ADDOPR value) : base(Terminals.ADDOPR)
        {
            this.value = value;
        }

        public override string ToString()
        {
            return $"({base.ToString()},{value})";
        }
    }

    public class BoolOpr : Token
    {
        public BOOLOPR value { get; private set; }

        public BoolOpr(BOOLOPR value) : base(Terminals.BOOLOPR)
        {
            this.value = value;
        }

        public override string ToString()
        {
            return $"({base.ToString()},{value})";
        }
    }

    public class Type : Token
    {
        public TYPE value { get; private set; }

        public Type(TYPE value) : base(Terminals.TYPE)
        {
            this.value = value;
        }

        public override string ToString()
        {
            return $"({base.ToString()},{value})";
        }
    }

    public class ChangeMode : Token
    {
        public CHANGEMODE value { get; private set; }

        public ChangeMode(CHANGEMODE value) : base(Terminals.CHANGEMODE)
        {
            this.value = value;
        }

        public override string ToString()
        {
            return $"({base.ToString()},{value})";
        }
    }

    public class MechMode : Token
    {
        public MECHMODE value { get; private set; }

        public MechMode(MECHMODE value) : base(Terminals.MECHMODE)
        {
            this.value = value;
        }

        public override string ToString()
        {
            return $"({base.ToString()},{value})";
        }
    }

    public class FlowMode : Token
    {
        public FLOWMODE value { get; private set; }

        public FlowMode(FLOWMODE value) : base(Terminals.FLOWMODE)
        {
            this.value = value;
        }

        public override string ToString()
        {
            return $"({base.ToString()},{value})";
        }
    }

    public class Ident : Token
    {
        public string value { get; private set; }

        public Ident(string value) : base(Terminals.IDENT)
        {
            this.value = value;
        }

        public override string ToString()
        {
            return $"({base.ToString()},\"{value}\")";
        }
    }
}
