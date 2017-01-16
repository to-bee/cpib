using IML_Compiler.IMLParser.DataTypes.Abstract;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class OptionalElseEps : IOptionalElse
    {
        public IAbsList<IAbsCmd> ToAbySyn()
        {
            return new AbsEmptyList<IAbsCmd>();
        }
    }

    class OptionalElseCmd : IOptionalElse
    {
        public IBlockCmd BlockCmd { get; private set; }

        public OptionalElseCmd(IBlockCmd cmd)
        {
            BlockCmd = cmd;
        }

        public IAbsList<IAbsCmd> ToAbySyn()
        {
            return BlockCmd.ToAbySyn();
        }
    }
}
