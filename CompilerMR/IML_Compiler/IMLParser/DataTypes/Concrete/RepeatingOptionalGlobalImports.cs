using IML_Compiler.IMLParser.DataTypes.Abstract;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class RepeatingOptionalGlobalImportsEps : IRepeatingOptionalGlobalImports
    {
        public IAbsList<AbsGlobalImport> ToAbySyn()
        {
            return new AbsEmptyList<AbsGlobalImport>();
        }
    }

    class RepeatingOptionalGlobalImportsImport : IRepeatingOptionalGlobalImports
    {
        public IGlobalImport GlobalImport { get; private set; }
        public IRepeatingOptionalGlobalImports RepeatingOptionalGlobalImports { get; private set; }

        public RepeatingOptionalGlobalImportsImport(IGlobalImport import,
            IRepeatingOptionalGlobalImports imports)
        {
            GlobalImport = import;
            RepeatingOptionalGlobalImports = imports;
        }

        public IAbsList<AbsGlobalImport> ToAbySyn()
        {
            return new AbsGlobalImportList()
            {
                GlobalImport = this.GlobalImport.ToAbySyn(),
                GlobalImports = RepeatingOptionalGlobalImports.ToAbySyn()
            };
        }
    }
}
