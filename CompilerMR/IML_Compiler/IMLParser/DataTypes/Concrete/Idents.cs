using IML_Compiler.IMLParser.DataTypes.Abstract;
using IML_Compiler.IMLScanner.DataTypes;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class Idents : IIdents
    {
        public Ident Ident { get; private set; }
        public IRepeatingOptionalIdents RepeatingOptionalIdents { get; private set; }

        public Idents(Ident ident, IRepeatingOptionalIdents idents)
        {
            Ident = ident;
            RepeatingOptionalIdents = idents;
        }

        public IAbsList<string> ToAbySyn()
        {
            return new AbsIdentList() {Ident = Ident.value, IdentList = RepeatingOptionalIdents.ToAbySyn()};
        }
    }
}
