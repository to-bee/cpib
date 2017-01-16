using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using IML_Compiler.IMLParser.DataTypes.Abstract;

namespace IML_Compiler.IMLParser.Logic
{
    // hacky/fancy reflection for adding a ToStr() method to all IAbsSyn implementing classes
    public static class AbsSynExtension
    {
        public static string ToStr(this IAbsSyn ias)
        {
            var res = ToStr(ias, "");
            return res;
        }
        public static string ToStr(this IAbsSyn ias, string intend)
        {
            string newline = "\r\n";

            var name = ias.GetType().Name.StartsWith("Abs") ? ias.GetType().Name.Substring(3) : ias.GetType().Name;

            string str = $"{intend}[{name}";

            foreach (var p in ias.GetType().GetProperties().Where(pr => !(pr.GetValue(ias) is IAbsSyn)))
            {
                str += ", ";
                if (p.GetValue(ias) == null)
                {
                    str += $"{p.Name} = NULL";
                }
                else
                {
                    str += $"{p.Name} = {p.GetValue(ias).ToString()}";
                }
            }

            bool subMembers = false;
            foreach (var p in ias.GetType().GetProperties().Where(pr => pr.GetValue(ias) is IAbsSyn))
            {
                subMembers = true;
                str += $", {newline}";
                str += (p.GetValue(ias) as IAbsSyn).ToStr(intend + "   ");
                str += newline;
            }

            if (subMembers) str += intend;
            str += "]";

            return str;
        }

        public static int Depth(this IAbsSyn ints)
        {
            var lengths = new List<int>();
            foreach (var p in ints.GetType().GetProperties().Where(pr => pr.GetValue(ints) is IAbsSyn))
            {
                lengths.Add((p.GetValue(ints) as IAbsSyn).Depth());
            }

            if (lengths.Count == 0) return 0;

            return lengths.Max() + 1;
        }
    }

}
