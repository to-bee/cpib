using IML_Compiler.IMLParser.DataTypes.Abstract;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class DeclarationStorage : IDeclaration
    {
        public IStorageDeclaration StorageDeclaration { get; private set; }

        public DeclarationStorage(IStorageDeclaration decl)
        {
            StorageDeclaration = decl;
        }

        public IAbsDeclaration ToAbySyn()
        {
            var abs = StorageDeclaration.ToAbySyn();
            return new AbsStorageDeclaration()
            {
                Ident = abs.Ident,
                ChangeMode = abs.ChangeMode,
                TypeDeclaration = abs.TypeDeclaration
            };
        }
    }

    class DeclarationFunction : IDeclaration
    {
        public IFunctionDeclaration FunctionDeclaration { get; private set; }

        public DeclarationFunction(IFunctionDeclaration decl)
        {
            FunctionDeclaration = decl;
        }

        public IAbsDeclaration ToAbySyn()
        {
            return FunctionDeclaration.ToAbySyn();
        }
    }

    class DeclarationProcedure : IDeclaration
    {
        public IProcedureDeclaration ProcedureDeclaration { get; private set; }

        public DeclarationProcedure(IProcedureDeclaration decl)
        {
            ProcedureDeclaration = decl;
        }

        public IAbsDeclaration ToAbySyn()
        {
            return ProcedureDeclaration.ToAbySyn();
        }
    }
}
