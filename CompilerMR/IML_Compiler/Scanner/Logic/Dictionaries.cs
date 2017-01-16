using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using IML_Compiler.IMLScanner.DataTypes;

namespace IML_Compiler.IMLScanner.Logic
{
    /// <summary>
    /// Contains a dictionary for resovling strings into terminals
    /// </summary>
    class Dictionaries
    {
        public static Dictionary<string, Terminals> dicTerm;

        static Dictionaries()
        {
            dicTerm = new Dictionary<string, Terminals>
            {
                {"(", Terminals.LPAREN},
                {")", Terminals.RPAREN},
                {",", Terminals.COMMA},
                {";", Terminals.SEMICOLON},
                {":", Terminals.COLON},
                {":=", Terminals.BECOMES},
                {"*", Terminals.MULTOPR},
                {"+", Terminals.ADDOPR},
                {"-", Terminals.ADDOPR},
                {"=", Terminals.RELOPR},
                {"/=", Terminals.RELOPR},
                {"<", Terminals.RELOPR},
                {">", Terminals.RELOPR},
                {"<=", Terminals.RELOPR},
                {">=", Terminals.RELOPR},
                {"&&", Terminals.BOOLOPR},
                {"||", Terminals.BOOLOPR},
                {"&?", Terminals.BOOLOPR},
                {"|?", Terminals.BOOLOPR},
                {"bool", Terminals.TYPE},
                {"call", Terminals.CALL},
                {"const", Terminals.CHANGEMODE},
                {"copy", Terminals.MECHMODE},
                {"debugin", Terminals.DEBUGIN},
                {"debugout", Terminals.DEBUGOUT},
                {"divE", Terminals.MULTOPR},
                {"do", Terminals.DO},
                {"else", Terminals.ELSE},
                {"endfun", Terminals.ENDFUN},
                {"endif", Terminals.ENDIF},
                {"endproc", Terminals.ENDPROC},
                {"endprogram", Terminals.ENDPROGRAM},
                {"endwhile", Terminals.ENDWHILE},
                {"false", Terminals.LITERAL},
                {"fun", Terminals.FUN},
                {"global", Terminals.GLOBAL},
                {"if", Terminals.IF},
                {"in", Terminals.FLOWMODE},
                {"init", Terminals.INIT},
                {"inout", Terminals.FLOWMODE},
                {"int32", Terminals.TYPE },
                {"int64", Terminals.TYPE},
                {"local", Terminals.LOCAL},
                {"modE", Terminals.MULTOPR},
                {"not", Terminals.NOTOPER},
                {"out", Terminals.FLOWMODE},
                {"proc", Terminals.PROC},
                {"program", Terminals.PROGRAM},
                {"ref", Terminals.MECHMODE},
                {"returns", Terminals.RETURNS},
                {"skip", Terminals.SKIP},
                {"then", Terminals.THEN},
                {"true", Terminals.LITERAL},
                {"var", Terminals.CHANGEMODE},
                {"while", Terminals.WHILE},
                {"[", Terminals.LCAST },
                { "]", Terminals.RCAST },
                {"float", Terminals.TYPE },
                //{".", Terminals.DOT },
                {"POSITIVE_INFINITY", Terminals.LITERAL },
                {"NEGATIVE_INFINITY", Terminals.LITERAL },
                {"NaN", Terminals.LITERAL }
            };

        }
    }
}
