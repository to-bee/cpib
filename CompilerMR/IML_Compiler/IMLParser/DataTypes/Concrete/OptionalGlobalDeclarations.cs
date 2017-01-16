using IML_Compiler.IMLParser.DataTypes.Abstract;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class OptionalGlobalDeclarationsEps : IOptionalGlobalDeclarations
    {
        public IAbsList<IAbsDeclaration> ToAbySyn()
        {
            return new AbsEmptyList<IAbsDeclaration>();
        }
    }

    class OptionalGlobalDeclarationsDecl : IOptionalGlobalDeclarations
    {
        public IDeclarations Declarations { get; private set; }

        public OptionalGlobalDeclarationsDecl (IDeclarations decls)
        {
            Declarations = decls;
        }

        public IAbsList<IAbsDeclaration> ToAbySyn()
        {
            return new AbsGlobalDeclarationsList() {GlobalDeclarationsList = Declarations.ToAbySyn()};
        }
    }
}
