using System;
using IML_Compiler.IMLParser.Logic;
using IML_Compiler.IMLScanner.DataTypes;

namespace IML_Compiler.IMLParser.DataTypes.Abstract
{
    public class AbsTypedIdent : IAbsSyn
    {
        public string Ident { get; set; }
        public TYPE Type { get; set; }
        public void Check()
        {
            GlobalContext.CurrentContext.AddVariable(Ident, Type);
        }

        public int Code(int loc)
        {
            //Note: this class probably is never used after creation of the abs tree.
            throw new NotSupportedException("Implement at a higher abstraction level");
        }
    }
}
