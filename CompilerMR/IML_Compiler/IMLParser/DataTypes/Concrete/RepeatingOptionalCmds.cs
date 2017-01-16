using IML_Compiler.IMLParser.DataTypes.Abstract;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class RepeatingOptionalCmdsEps : IRepeatingOptionalCmds
    {
        public IAbsList<IAbsCmd> ToAbySyn()
        {
            return new AbsEmptyList<IAbsCmd>();
        }
    }

    class RepeatingOptionalCmdsCmd : IRepeatingOptionalCmds
    {
        public ICmd Cmd { get; private set; }
        public IRepeatingOptionalCmds RepeatingOptionalCmds { get; private set; }

        public RepeatingOptionalCmdsCmd(ICmd cmd, IRepeatingOptionalCmds cmds)
        {
            Cmd = cmd;
            RepeatingOptionalCmds = cmds;
        }

        public IAbsList<IAbsCmd> ToAbySyn()
        {
            return new AbsCmdList() {Cmd = Cmd.ToAbySyn(), CmdList = RepeatingOptionalCmds.ToAbySyn()};
        }
    }
}
