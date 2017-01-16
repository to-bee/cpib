using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IML_Compiler.IMLScanner.DataTypes
{
    /*
     * 
     * Possible terminals and they're possible arguments
     * 
     */

    public enum Terminals
    {
        LPAREN,
        RPAREN,
        COMMA,
        SEMICOLON,
        COLON,
        BECOMES,
        MULTOPR,
        ADDOPR,
        RELOPR,
        BOOLOPR,
        TYPE,
        CALL,
        CHANGEMODE,
        MECHMODE,
        DEBUGIN,
        DEBUGOUT,
        DO,
        ELSE,
        ENDFUN,
        ENDIF,
        ENDPROC,
        ENDPROGRAM,
        ENDWHILE,
        LITERAL,
        //INTLITERAL,
        //FLOATLITERAL,
        //BOOLLITERAL,
        FUN,
        GLOBAL,
        IF,
        FLOWMODE,
        INIT,
        LOCAL,
        NOTOPER,
        PROC,
        PROGRAM,
        RETURNS,
        SKIP,
        THEN,
        WHILE,
        IDENT,
        SENTINEL,
        //DOT,
        LCAST,
        RCAST
    };

    public enum RELOPR
    {
        EQ,
        NE,
        LT,
        GT,
        LE,
        GE
    };

    public enum MULTOPR
    {
        TIMES,
        DIV_E,
        MOD_E
    }

    public enum ADDOPR
    {
        PLUS,
        MINUS
    }

    public enum BOOLOPR
    {
        AND,
        OR,
        CAND,
        COR
    }

    public enum TYPE
    {
        BOOL,
        INT32,
        INT64,
        FLOAT
    }

    public enum CHANGEMODE
    {
        CONST,
        VAR
    }

    public enum MECHMODE
    {
        COPY,
        REF
    }

    public enum LITERAL
    {
        BOOL,
        INT32,
        INT64,
        FLOAT
    }

    public enum FLOWMODE
    {
        IN,
        INOUT,
        OUT
    }
}
