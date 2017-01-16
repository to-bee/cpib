using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IML_Compiler.IMLParser.DataTypes.CodeCreation
{
    public interface IInstruction
    {
        string Export();
    }

    public abstract class AbsInstruction : IInstruction
    {
        public virtual string Export()
        {
            var s = GetType().ToString();
            return s.Substring(s.LastIndexOf(".", StringComparison.CurrentCultureIgnoreCase) + 1);
        }
    }
}
