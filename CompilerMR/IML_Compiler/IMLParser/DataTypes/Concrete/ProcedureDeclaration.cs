using IML_Compiler.IMLParser.DataTypes.Abstract;
using IML_Compiler.IMLScanner.DataTypes;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class ProcedureDeclaration : IProcedureDeclaration
    {
        public Ident Ident { get; private set; }
        public IParameterList ParameterList { get; private set; }
        public IOptionalGlobalImports OptionalGlobalImports { get; private set; }
        public IOptionalLocalStorageDeclarations OptionalLocalStorageDeclarations { get; private set; }
        public IBlockCmd BlockCmd { get; private set; }

        public ProcedureDeclaration (Ident ident, IParameterList paraml, IOptionalGlobalImports imports,
            IOptionalLocalStorageDeclarations storageDecl, IBlockCmd cmd)
        {
            Ident = ident;
            ParameterList = paraml;
            OptionalGlobalImports = imports;
            OptionalLocalStorageDeclarations = storageDecl;
            BlockCmd = cmd;
        }

        public AbsProcedureDeclaration ToAbySyn()
        {
            return new AbsProcedureDeclaration()
            {
                BlockCmd = BlockCmd.ToAbySyn(),
                Ident = Ident.value,
                GlobalImports = OptionalGlobalImports.ToAbySyn(),
                ParameterList = ParameterList.ToAbySyn(),
                LocalStorageDeclarations = OptionalLocalStorageDeclarations.ToAbySyn()
            };
        }
    }
}
