using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IML_Compiler.IMLScanner.DataTypes
{
    public interface ITokenList
    {
        /// <summary>
        /// Adds a token
        /// </summary>
        /// <param name="token">The token to be added</param>
        void Add(Token token);

        /// <summary>
        /// Resets the pointer to the beginning of the list
        /// </summary>
        void Reset();

        /// <summary>
        /// Gets the next token from the list
        /// </summary>
        /// <returns>Returns this token</returns>
        Token NextToken();

        /// <summary>
        /// Creates one string including all tokens in the list
        /// </summary>
        /// <returns>The generated string</returns>
        string ToString();
    }
}
