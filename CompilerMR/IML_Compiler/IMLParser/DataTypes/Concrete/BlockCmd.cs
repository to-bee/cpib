using IML_Compiler.IMLParser.DataTypes.Abstract;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class BlockCmd : IBlockCmd
    {
        public ICmd Cmd { get; private set; }
        public IRepeatingOptionalCmds RepeatingOptionalCmds { get; private set; }

        public BlockCmd(ICmd cmd, IRepeatingOptionalCmds repeatingOptionalCmds)
        {
            Cmd = cmd;
            RepeatingOptionalCmds = repeatingOptionalCmds;
        }

        public IAbsList<IAbsCmd> ToAbySyn()
        {
            return new AbsCmdList() {Cmd = Cmd.ToAbySyn(), CmdList = RepeatingOptionalCmds.ToAbySyn()};
        }
    }
}
