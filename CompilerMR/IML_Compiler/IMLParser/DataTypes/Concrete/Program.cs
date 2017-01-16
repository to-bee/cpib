using IML_Compiler.IMLParser.DataTypes.Abstract;
using IML_Compiler.IMLScanner.DataTypes;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class Program : IProgram
    {
        public IProgramParameterList ProgramParameterList { get; private set; }
        public IOptionalGlobalDeclarations OptionalGlobalDeclarations { get; private set; }
        public IBlockCmd BlockCmd { get; private set; }
        public Ident Ident { get; private set; }

        public Program (Ident ident, IProgramParameterList programParameterList, 
            IOptionalGlobalDeclarations optionalGlobalDeclarations, IBlockCmd blockCmd)
        {
            ProgramParameterList = programParameterList;
            OptionalGlobalDeclarations = optionalGlobalDeclarations;
            BlockCmd = blockCmd;
            Ident = ident;   
        }

        public AbsProgram ToAbsSyn()
        {
            return new AbsProgram()
            {
                BlockCmd = BlockCmd.ToAbySyn(),
                Ident = Ident.value,
                GlobalDeclaractions = OptionalGlobalDeclarations.ToAbySyn(),
                ProgramParameterList = ProgramParameterList.ToAbySyn()
            };
        }
    }
}
