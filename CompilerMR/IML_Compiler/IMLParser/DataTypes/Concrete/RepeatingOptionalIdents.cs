using IML_Compiler.IMLParser.DataTypes.Abstract;
using IML_Compiler.IMLScanner.DataTypes;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class RepeatingOptionalIdentsEps : IRepeatingOptionalIdents
    {
        public IAbsList<string> ToAbySyn()
        {
            return new AbsEmptyList<string>();
        }
    }

    class RepeatingOptionalIdentsIdent : IRepeatingOptionalIdents
    {
        public IRepeatingOptionalIdents RepeatingOptionalIdents { get; private set; }
        public Ident Ident { get; private set; }

        public RepeatingOptionalIdentsIdent(Ident ident, IRepeatingOptionalIdents idents)
        {
            Ident = ident;
            RepeatingOptionalIdents = idents;
        }

        public IAbsList<string> ToAbySyn()
        {
            return new AbsIdentList() {Ident = this.Ident.value, IdentList = RepeatingOptionalIdents.ToAbySyn()};
        }
    }
}
