using IML_Compiler.IMLScanner.DataTypes;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class OptionalFlowModeEps : IOptionalFlowMode
    {
        public FLOWMODE ToAbsSyn()
        {
            return FLOWMODE.IN;
        }
    }

    class OptionalFlowModeMode : IOptionalFlowMode
    {
        public FlowMode FlowMode { get; private set; }

        public OptionalFlowModeMode(FlowMode mode)
        {
            FlowMode = mode;
        }

        public FLOWMODE ToAbsSyn()
        {
            return FlowMode.value;
        }
    }
}
