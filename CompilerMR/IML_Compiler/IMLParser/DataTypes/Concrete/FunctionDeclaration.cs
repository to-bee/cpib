using IML_Compiler.IMLParser.DataTypes.Abstract;
using IML_Compiler.IMLScanner.DataTypes;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class FunctionDeclaration : IFunctionDeclaration
    {
        public Ident Ident { get; private set; }
        public IParameterList ParameterList { get; private set; }
        public IStorageDeclaration StorageDeclaration { get; private set; }
        public IOptionalGlobalImports OptionalGlobalImports { get; private set; }
        public IOptionalLocalStorageDeclarations OptionalLocalStorageDeclarations { get; private set; }
        public IBlockCmd BlockCmd { get; private set; }

        public FunctionDeclaration (Ident ident, IParameterList paramList,
            IStorageDeclaration storageDecl, IOptionalGlobalImports imports,
            IOptionalLocalStorageDeclarations optStorageDecl, IBlockCmd cmd)
        {
            Ident = ident;
            ParameterList = paramList;
            StorageDeclaration = storageDecl;
            OptionalGlobalImports = imports;
            OptionalLocalStorageDeclarations = optStorageDecl;
            BlockCmd = cmd;
        }

        public AbsFunctionDeclaration ToAbySyn()
        {
            return new AbsFunctionDeclaration()
            {
                BlockCmd = this.BlockCmd.ToAbySyn(),
                Ident = this.Ident.value,
                GlobalImports = OptionalGlobalImports.ToAbySyn(),
                ParameterList = this.ParameterList.ToAbySyn(),
                ReturnType = StorageDeclaration.ToAbySyn(),
                LocalStorageDeclarations = OptionalLocalStorageDeclarations.ToAbySyn()
            };
        }
    }
}
