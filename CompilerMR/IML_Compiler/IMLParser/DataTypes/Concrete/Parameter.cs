using IML_Compiler.IMLParser.DataTypes.Abstract;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class Parameter : IParameter
    {
        public IOptionalFlowMode FlowMode { get; private set; }
        public IOptionalMechMode MechMode { get; private set; }
        public IStorageDeclaration StorageDeclaration { get; private set; }

        public Parameter (IOptionalFlowMode fMode, IOptionalMechMode mMode, IStorageDeclaration decl)
        {
            FlowMode = fMode;
            MechMode = mMode;
            StorageDeclaration = decl;
        }

        public AbsParameter ToAbySyn()
        {
            var abs = StorageDeclaration.ToAbySyn();
            return new AbsParameter()
            {
                StorageDeclaration = StorageDeclaration.ToAbySyn(),
                FlowMode = FlowMode.ToAbsSyn(),
                MechMode = MechMode.ToAbsSyn()
            };
        }
    }
}
