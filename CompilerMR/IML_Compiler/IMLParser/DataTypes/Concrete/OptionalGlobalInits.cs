using IML_Compiler.IMLParser.DataTypes.Abstract;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class OptionalGlobalInitsEps : IOptionalGlobalInits
    {
        public IAbsList<string> ToAbySyn()
        {
            return new AbsEmptyList<string>();
        }
    }

    class OptionalGlobalInitsInit : IOptionalGlobalInits
    {
        public IIdents Idents { get; private set; }

        public OptionalGlobalInitsInit(IIdents idents)
        {
            Idents = idents;
        }

        public IAbsList<string> ToAbySyn()
        {
            return new AbsIdentList() {IdentList = Idents.ToAbySyn()};
        }
    }
}
