using IML_Compiler.IMLParser.DataTypes.Abstract;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class RepeatingOptionalDeclarationsEps : IRepeatingOptionalDeclarations
    {
        public IAbsList<IAbsDeclaration> ToAbySyn()
        {
            return new AbsEmptyList<IAbsDeclaration>();
        }
    }

    class RepeatingOptionalDeclarationsDecl : IRepeatingOptionalDeclarations
    {
        public IDeclaration Declaration { get; private set; }
        public IRepeatingOptionalDeclarations RepeatingOptionalDeclarations { get; private set; }

        public RepeatingOptionalDeclarationsDecl (IDeclaration decl, IRepeatingOptionalDeclarations decls)
        {
            Declaration = decl;
            RepeatingOptionalDeclarations = decls;
        }

        public IAbsList<IAbsDeclaration> ToAbySyn()
        {
            return new AbsGlobalDeclarationsList() {GlobalDeclaration = Declaration.ToAbySyn(), GlobalDeclarationsList = RepeatingOptionalDeclarations.ToAbySyn()};
        }
    }
}
