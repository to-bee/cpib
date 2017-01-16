using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IML_Compiler.IMLScanner.DataTypes
{
    class TokenList : ITokenList
    {
        private List<Token> tokens;
        private int pos;

        public TokenList()
        {
            tokens = new List<Token>();
            pos = 0;
        }


        public void Add(Token token)
        {
            tokens.Add(token);
        }

        public void Reset()
        {
            pos = 0;
        }

        public Token NextToken()
        {
            if (pos >= tokens.Count) return null;

            return tokens[pos++];
        }

        public override string ToString()
        {
            var str = tokens.Select(a => a.ToString()).Aggregate((a, b) => a + ", " + b);
            return $"[{str}]";
        }
    }
}
