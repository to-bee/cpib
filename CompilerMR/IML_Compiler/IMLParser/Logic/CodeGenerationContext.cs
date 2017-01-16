using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using IML_Compiler.IMLParser.DataTypes.CodeCreation;

namespace IML_Compiler.IMLParser.Logic
{
    public class CodeGenerationContext
    {
        public static IInstruction[] Instructions = 
            new IInstruction[10000];

        public static string Export()
        {
            var sb = new StringBuilder();
            foreach (var instr in Instructions)
            {
                if (instr == null)
                    break;

                sb.Append(instr.Export());
                sb.Append(";");
            }
            return sb.ToString();
        }
    }
}
