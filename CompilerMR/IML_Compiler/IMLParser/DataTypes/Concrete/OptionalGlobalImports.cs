using IML_Compiler.IMLParser.DataTypes.Abstract;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class OptionalGlobalImportsEps : IOptionalGlobalImports
    {
        public IAbsList<AbsGlobalImport> ToAbySyn()
        {
            return new AbsEmptyList<AbsGlobalImport>();
        }
    }

    class OptionalGlobalImportsImport : IOptionalGlobalImports
    {
        public IGlobalImport GlobalImport { get; private set; }
        public IRepeatingOptionalGlobalImports RepeatingOptionalGlobalImports { get; private set; }

        public OptionalGlobalImportsImport(IGlobalImport import, 
            IRepeatingOptionalGlobalImports imports)
        {
            GlobalImport = import;
            RepeatingOptionalGlobalImports = imports;
        }

        public IAbsList<AbsGlobalImport> ToAbySyn()
        {
            return new AbsGlobalImportList()
            {
                GlobalImport = GlobalImport.ToAbySyn(),
                GlobalImports = RepeatingOptionalGlobalImports.ToAbySyn()
            };
        }
    }
}
