using IML_Compiler.IMLScanner.DataTypes;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class OptionalMechModeEps : IOptionalMechMode
    {
        public MECHMODE ToAbsSyn()
        {
            return MECHMODE.COPY;
        }
    }

    class OptionalMechModeMode : IOptionalMechMode
    {
        public MechMode MechMode { get; private set; }

        public OptionalMechModeMode(MechMode mode)
        {
            MechMode = mode;
        }

        public MECHMODE ToAbsSyn()
        {
            return MechMode.value;
        }
    }
}
