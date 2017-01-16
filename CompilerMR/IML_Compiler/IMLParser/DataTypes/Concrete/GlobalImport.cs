using IML_Compiler.IMLParser.DataTypes.Abstract;
using IML_Compiler.IMLScanner.DataTypes;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class GlobalImport : IGlobalImport
    {
        public IOptionalFlowMode OptionalFlowMode { get; private set; }
        public IOptionalChangeMode OptionalChangeMode { get; private set; }
        public Ident Ident { get; private set; }

        public GlobalImport (IOptionalFlowMode flowMode, IOptionalChangeMode changeMode,
            Ident ident)
        {
            OptionalFlowMode = flowMode;
            OptionalChangeMode = changeMode;
            Ident = ident;
        }

        public AbsGlobalImport ToAbySyn()
        {
            return new AbsGlobalImport()
            {
                ChangeMode = OptionalChangeMode.ToAbySyn(),
                FlowMode = OptionalFlowMode.ToAbsSyn(),
                Ident = this.Ident.value
            };
        }
    }
}
