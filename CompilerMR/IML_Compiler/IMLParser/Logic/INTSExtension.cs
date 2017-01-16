using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using IML_Compiler.IMLParser.DataTypes.Concrete;

namespace IML_Compiler.IMLParser.Logic
{
    // hacky/fancy reflection for adding a ToStr() method to all INTS implementing classes
    public static class NonTerminalSymbolExtensions
    {
        public static string ToStr(this INonTerminalSymbol ints)
        {
            return ToStr(ints, "");
        }
        public static string ToStr(this INonTerminalSymbol ints, string intend)
        {
            string newline = "\r\n";

            string str = $"{intend}[{ints.GetType().Name}";

            foreach (var p in ints.GetType().GetProperties().Where(pr => !(pr.GetValue(ints) is INonTerminalSymbol)))
            {
                str += ", ";
                if (p.GetValue(ints) == null)
                {
                    str += $"{p.Name} = NULL";
                }
                else if (p.GetValue(ints).GetType().GetMethod("ToString").DeclaringType == p.GetValue(ints).GetType())
                {
                    str += $"{p.Name} = {p.GetValue(ints).ToString()}";
                }
                else
                {
                    str += $"{p.Name}";
                }
            }

            bool subMembers = false;
            foreach (var p in ints.GetType().GetProperties().Where(pr => pr.GetValue(ints) is INonTerminalSymbol))
            {
                subMembers = true;
                str += $", {newline}";
                str += (p.GetValue(ints) as INonTerminalSymbol).ToStr(intend + "   ");
                str += newline;
            }

            if (subMembers) str += intend;
            str += "]";

            return str;
        }

        public static int Depth(this INonTerminalSymbol ints)
        {
            var lengths = new List<int>();
            foreach(var p in ints.GetType().GetProperties().Where(pr => pr.GetValue(ints) is INonTerminalSymbol))
            {
                lengths.Add((p.GetValue(ints) as INonTerminalSymbol).Depth());
            }

            if (lengths.Count == 0) return 0;

            return lengths.Max() + 1;
        }
    }

}
