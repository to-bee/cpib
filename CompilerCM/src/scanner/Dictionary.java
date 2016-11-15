package scanner;

import java.util.HashMap;
import java.util.Map;

import framework.IToken;
import terminal.Terminals;
import token.BaseToken;
import token.DataType;
import token.Operator;
import types.Type;
import types.Operators;

/** 
 * A separate dictionary containing all symbols, keywords and operators.
 * @author Simon Meier
 *
 */
public class Dictionary {

	private final Map<String, IToken> keywords;
	private final Map<String, IToken> operators;

	Dictionary() {
		keywords = new HashMap<>();
		operators = new HashMap<>();

		// Datatypes
		keywords.put("bool", new DataType(Terminals.TYPEIDENT, Type.BOOL));
		keywords.put("int32", new DataType(Terminals.TYPEIDENT, Type.INT32));
		keywords.put("int64", new DataType(Terminals.TYPEIDENT, Type.INT64));

		// Symbols
		keywords.put("(", new BaseToken(Terminals.LPAREN));
		keywords.put(")", new BaseToken(Terminals.RPAREN));
		keywords.put(",", new BaseToken(Terminals.COMMA));
		keywords.put(";", new BaseToken(Terminals.SEMICOLON));
		keywords.put(":", new BaseToken(Terminals.COLON));
		keywords.put(":=", new BaseToken(Terminals.BECOMES));

		// Terminals
		keywords.put("call", new BaseToken(Terminals.CALL));
		keywords.put("const", new BaseToken(Terminals.CONST));
		keywords.put("copy", new BaseToken(Terminals.COPY));
		keywords.put("debugin", new BaseToken(Terminals.DEBUGIN));
		keywords.put("debugout", new BaseToken(Terminals.DEBUGOUT));
		keywords.put("dive", new BaseToken(Terminals.DIVE));
		keywords.put("do", new BaseToken(Terminals.DO));
		keywords.put("else", new BaseToken(Terminals.ELSE));
		keywords.put("endfun", new BaseToken(Terminals.ENDFUN));
		keywords.put("endif", new BaseToken(Terminals.ENDIF));
		keywords.put("endproc", new BaseToken(Terminals.ENDPROC));
		keywords.put("endprogram", new BaseToken(Terminals.ENDPROGRAM));
		keywords.put("endwhile", new BaseToken(Terminals.ENDWHILE));
		keywords.put("false", new BaseToken(Terminals.FALSE));
		keywords.put("fun", new BaseToken(Terminals.FUN));
		keywords.put("global", new BaseToken(Terminals.GLOBAL));
		keywords.put("if", new BaseToken(Terminals.IF));
		keywords.put("in", new BaseToken(Terminals.IN));
		keywords.put("init", new BaseToken(Terminals.INIT));
		keywords.put("inout", new BaseToken(Terminals.INOUT));
		keywords.put("mode", new BaseToken(Terminals.MODE));
		keywords.put("not", new BaseToken(Terminals.NOT));
		keywords.put("out", new BaseToken(Terminals.OUT));
		keywords.put("proc", new BaseToken(Terminals.PROC));	
		keywords.put("program", new BaseToken(Terminals.PROGRAM));
		keywords.put("ref", new BaseToken(Terminals.REF));
		keywords.put("returns", new BaseToken(Terminals.RETURNS));
		keywords.put("skip", new BaseToken(Terminals.SKIP));
		keywords.put("then", new BaseToken(Terminals.THEN));
		keywords.put("true", new BaseToken(Terminals.TRUE));
		keywords.put("var", new BaseToken(Terminals.VAR));
		keywords.put("while", new BaseToken(Terminals.WHILE));


		// Special sings
		keywords.put("sentinel", new BaseToken(Terminals.SENTINEL));
		keywords.put("literal", new BaseToken(Terminals.LITERAL));
		keywords.put("ident", new BaseToken(Terminals.IDENT));

		// Operators
		// RELOPR
		operators.put("==", new Operator(Terminals.RELOPR ,Operators.EQ));
		operators.put("!=", new Operator(Terminals.RELOPR, Operators.NE));
		operators.put("<=", new Operator(Terminals.RELOPR, Operators.LE));
		operators.put(">=", new Operator(Terminals.RELOPR, Operators.GE));
		operators.put(">", new Operator(Terminals.RELOPR, Operators.GT));
		operators.put("<", new Operator(Terminals.RELOPR, Operators.LT));
		
		// BOOLOPR
			
		operators.put("&&", new Operator(Terminals.BOOLOPR, Operators.AND));
		operators.put("||", new Operator(Terminals.BOOLOPR, Operators.OR));
		operators.put("!&", new Operator(Terminals.BOOLOPR, Operators.CAND));
		operators.put("!|", new Operator(Terminals.BOOLOPR, Operators.COR));
		
		// ADDOPR
		operators.put("+", new Operator(Terminals.ADDOPR, Operators.PLUS));
		operators.put("-", new Operator(Terminals.ADDOPR, Operators.MINUS));
		
		// MULTOPR
		operators.put("*", new Operator(Terminals.MULTOPR, Operators.TIMES));
		operators.put("divE", new Operator(Terminals.MULTOPR, Operators.DIV_E));
		operators.put("divF", new Operator(Terminals.MULTOPR, Operators.DIV_F));
		operators.put("divT", new Operator(Terminals.MULTOPR, Operators.DIV_T));
		operators.put("modE", new Operator(Terminals.MULTOPR, Operators.MOD_E));
		operators.put("modF", new Operator(Terminals.MULTOPR, Operators.MOD_F));
		operators.put("modT", new Operator(Terminals.MULTOPR, Operators.MOD_T));
	}
	
	public boolean matchOperator(String key){
		return operators.containsKey(key);
	}
	
	public boolean matchKeyword(String key){		
		return keywords.containsKey(key);
	}
	
	public IToken getOperator(String key){
		return operators.get(key);
	}
	
	public IToken getKeyword(String key){
		return keywords.get(key);
	}
	
}
