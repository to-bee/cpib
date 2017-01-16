using IML_Compiler.IMLParser.DataTypes.Abstract;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class StorageDeclaration : IStorageDeclaration
    {
        public IOptionalChangeMode OptionalChangeMode { get; private set; }
        public ITypedIdent TypedIdent { get; private set; }

        public StorageDeclaration (IOptionalChangeMode mode, ITypedIdent ident)
        {
            OptionalChangeMode = mode;
            TypedIdent = ident;
        }

        public AbsStorageDeclaration ToAbySyn()
        {
            var abs = TypedIdent.ToAbySyn();
            return new AbsStorageDeclaration()
            {
                ChangeMode = OptionalChangeMode.ToAbySyn(),
                Ident = abs.Ident,
                TypeDeclaration = abs.Type
            };
        }
    }
}
