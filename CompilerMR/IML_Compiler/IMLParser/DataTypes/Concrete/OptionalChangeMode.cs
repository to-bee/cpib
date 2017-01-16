using IML_Compiler.IMLScanner.DataTypes;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class OptionalChangeModeEps : IOptionalChangeMode
    {
        public CHANGEMODE ToAbySyn()
        {
            return CHANGEMODE.CONST;
        }
    }

    class OptionalChangeModeMode : IOptionalChangeMode
    {
        public ChangeMode ChangeMode { get; private set; }

        public OptionalChangeModeMode(ChangeMode mode)
        {
            ChangeMode = mode;
        }

        public CHANGEMODE ToAbySyn()
        {
            return ChangeMode.value;
        }
    }
}
