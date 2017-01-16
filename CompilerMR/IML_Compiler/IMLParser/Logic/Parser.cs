using IML_Compiler.IMLParser.DataTypes.Concrete;
using IML_Compiler.IMLScanner.DataTypes;

namespace IML_Compiler.IMLParser.Logic
{
    public partial class Parser
    {
        private ITokenList tokenList;
        public Token token { get; private set; }
        private Terminals terminal;
        public int Position { get; private set; } = 1;

        public Parser(ITokenList tokenList)
        {
            this.tokenList = tokenList;
            this.tokenList.Reset();

            token = this.tokenList.NextToken();
            terminal = token.Terminal;
        }

        public IProgram Parse()
        {
            var p = programm();

            Consume(Terminals.SENTINEL);

            return p;
        }

        public Token Consume(Terminals expectedTerminal)
        {
            if (terminal == expectedTerminal)
            {
                Token consumedToken = token;
                if (terminal != Terminals.SENTINEL)
                {
                    token = tokenList.NextToken();
                    terminal = token.Terminal;
                    Position++;
                }
                //Console.WriteLine(consumedToken);
                return consumedToken;
            } else
            {
                throw new ParserException($"Terminal expected: {expectedTerminal}, terminal found: {terminal} ({token.ToString()})");
            }
        }
    }
}
