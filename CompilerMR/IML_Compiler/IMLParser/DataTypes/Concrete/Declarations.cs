using IML_Compiler.IMLParser.DataTypes.Abstract;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class Declarations : IDeclarations
    {
        public IDeclaration Declaration { get; private set; }
        public IRepeatingOptionalDeclarations RepeatingOptionalDeclarations { get; private set; }

        public Declarations (IDeclaration decl, IRepeatingOptionalDeclarations decls)
        {
            Declaration = decl;
            RepeatingOptionalDeclarations = decls;
        }

        public IAbsList<IAbsDeclaration> ToAbySyn()
        {
            return new AbsGlobalDeclarationsList()
            {
                GlobalDeclaration = Declaration.ToAbySyn(),
                GlobalDeclarationsList = RepeatingOptionalDeclarations.ToAbySyn()
            };
        }
    }
}
