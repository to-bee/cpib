package scanner;

import java.util.HashMap;
import java.util.Map;

import scanner.token.BaseToken;
import scanner.token.DataTypeToken;
import scanner.token.IToken;
import scanner.token.OperatorToken;
import types.Datatypes;
import types.Operators;
import types.Terminals;

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
		keywords.put("bool", new DataTypeToken(Terminals.TYPE, Datatypes.BOOL));
		keywords.put("int32", new DataTypeToken(Terminals.TYPE, Datatypes.INT32));
		keywords.put("int64", new DataTypeToken(Terminals.TYPE, Datatypes.INT64));

		// Symbols
		keywords.put("(", new BaseToken(Terminals.LPAREN));
		keywords.put(")", new BaseToken(Terminals.RPAREN));
		keywords.put(",", new BaseToken(Terminals.COMMA));
		keywords.put(";", new BaseToken(Terminals.SEMICOLON));
		keywords.put(":", new BaseToken(Terminals.COLON));
		keywords.put(":=", new BaseToken(Terminals.BECOMES));
		keywords.put("=", new BaseToken(Terminals.EQUAL));

		keywords.put("multopr", new BaseToken(Terminals.MULTOPR));
		keywords.put("addopr", new BaseToken(Terminals.ADDOPR));
		keywords.put("relopr", new BaseToken(Terminals.RELOPR));
		keywords.put("boolopr", new BaseToken(Terminals.BOOLOPR));

		// Terminals
		keywords.put("call", new BaseToken(Terminals.CALL));
		// TODO rest einfügen ...
		keywords.put("program", new BaseToken(Terminals.PROGRAM));
		keywords.put("ref", new BaseToken(Terminals.REF));
		keywords.put("returns", new BaseToken(Terminals.RETURNS));
		keywords.put("skip", new BaseToken(Terminals.SKIP));
		keywords.put("then", new BaseToken(Terminals.THEN));
		keywords.put("true", new BaseToken(Terminals.TRUE));
		keywords.put("var", new BaseToken(Terminals.VAR));
		keywords.put("while", new BaseToken(Terminals.WHILE));
		keywords.put("endprogram", new BaseToken(Terminals.ENDPROGRAM));

		// Special sings
		keywords.put("sentinel", new BaseToken(Terminals.SENTINEL));
		keywords.put("literal", new BaseToken(Terminals.LITERAL));
		keywords.put("ident", new BaseToken(Terminals.IDENT));

		// Operators
		// TODO CNAD, COR
		operators.put("==", new OperatorToken(Terminals.BOOLOPR ,Operators.EQ));
		operators.put("!=", new OperatorToken(Terminals.BOOLOPR, Operators.NE));
		operators.put("!", new OperatorToken(Terminals.BOOLOPR, Operators.NOT));
		operators.put("<=", new OperatorToken(Terminals.RELOPR, Operators.LE));
		operators.put(">=", new OperatorToken(Terminals.RELOPR, Operators.GE));
		operators.put(">", new OperatorToken(Terminals.RELOPR, Operators.GT));
		operators.put("<", new OperatorToken(Terminals.RELOPR, Operators.LT));
		operators.put("+", new OperatorToken(Terminals.ADDOPR, Operators.PLUS));
		operators.put("-", new OperatorToken(Terminals.ADDOPR, Operators.MINUS));
		operators.put("*", new OperatorToken(Terminals.MULTOPR, Operators.TIMES));
		operators.put("/", new OperatorToken(Terminals.MULTOPR, Operators.DIV));
		operators.put("%", new OperatorToken(Terminals.MULTOPR, Operators.MOD));
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
