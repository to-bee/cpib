using IML_Compiler.IMLParser.DataTypes.Abstract;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class RepeatingOptionalStorageDeclarationsEps : IRepeatingOptionalStorageDeclarations
    {
        public IAbsList<AbsStorageDeclaration> ToAbySyn()
        {
            return new AbsEmptyList<AbsStorageDeclaration>();
        }
    }

    class RepeatingOptionalStorageDeclarationsDecl : IRepeatingOptionalStorageDeclarations
    {
        public IStorageDeclaration StorageDeclaration { get; private set; }
        public IRepeatingOptionalStorageDeclarations RepeatingOptionalStorageDeclarations { get; private set; }

        public RepeatingOptionalStorageDeclarationsDecl(IStorageDeclaration decl,
            IRepeatingOptionalStorageDeclarations decls)
        {
            StorageDeclaration = decl;
            RepeatingOptionalStorageDeclarations = decls;
        }

        public IAbsList<AbsStorageDeclaration> ToAbySyn()
        {
            return new AbsStorageDeclarationList()
            {
                StorageDeclaration = StorageDeclaration.ToAbySyn(),
                StorageDeclarationList = RepeatingOptionalStorageDeclarations.ToAbySyn()
            };
        }
    }
}
