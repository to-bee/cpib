package ch.fhnw.cbip.compiler.scanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.CharacterCodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import ch.fhnw.cbip.compiler.error.LexicalError;
import ch.fhnw.cbip.compiler.scanner.enums.Operators;
import ch.fhnw.cbip.compiler.scanner.enums.States;
import ch.fhnw.cbip.compiler.scanner.enums.Terminals;
import ch.fhnw.cbip.compiler.scanner.tokens.IdentToken;
import ch.fhnw.cbip.compiler.scanner.tokens.LiteralToken;

public class Scanner {

	private int lineNumber;
	private LinkedList<Token> tokenList;
	private States currentState = States.INIT;
	private String currentToken = "";
	private int currentIndex = 0;
	private Map<String, Terminals> terminalsMap = new HashMap<String, Terminals>();
	private Map<String, Operators> operatorsMap = new HashMap<String, Operators>();

	public Scanner() {
		initTerminalsMap();
		initOperatorsMap();
	}

	public LinkedList<Token> scan(BufferedReader input) throws IOException, LexicalError {
	        tokenList = new LinkedList<>(); // reset token list
	        String currentLine = "";
	        char[] currentCharArray = {};
	        lineNumber = 0;
	        
	        while((currentLine = input.readLine()) != null){
	        	
	        	currentLine += " ";
	        	currentCharArray = currentLine.toCharArray();
	        	
	        	while(currentIndex < currentCharArray.length){
	        		
	        		char currentChar = currentCharArray[currentIndex];
	        		
	        		switch (currentState) {
		        		case INIT:
		        			handleInitState(currentChar);
		        			break;
						case ERROR:
							handleErrorState();
							break;
						case IDENT:
							handleIdentState(currentChar);
							break;
						case LITERAL:
							handleLiteralState(currentChar);
							break;
						case OPERATOR:
							handleOperatorState(currentChar);
							break;
						default:
							break;
	        		}
	        		
	        		currentIndex++;
	        	}
	        	
	        	currentIndex = 0;
	        	lineNumber++;
	        }
	        
	        
	        return tokenList;
	    }

	private void handleOperatorState(char currentChar) {
		String s = currentChar + "";
		if (!s.matches("[=&|?]+") && currentToken.length() == 1) {
			currentIndex--;
			currentState = States.INIT;
			
			Terminals terminal = terminalsMap.get(currentToken);
			Operators operator = operatorsMap.get(currentToken);
			if (terminal != null && operator != null) {
				tokenList.add(new Token(terminal, operator));
			} else if (terminal != null && operator == null){
				tokenList.add(new Token(terminal, null));
			} else {
				currentState = States.ERROR;
			}
		} else if (currentToken.length() == 1) {
			currentState = States.INIT;
			
			currentToken += currentChar;
			Terminals terminal = terminalsMap.get(currentToken);
			Operators operator = operatorsMap.get(currentToken);
			if (terminal != null && operator != null) {
				tokenList.add(new Token(terminal, operator));
			} else if (terminal != null && operator == null) {
				tokenList.add(new Token(terminal, null));
			} else {
				currentState = States.ERROR;
			}
			
		} else {
			currentState = States.ERROR;
		}
		
	}

	private void handleIdentState(char currentChar) {
		if(!Character.isDigit(currentChar) && !Character.isAlphabetic(currentChar)){
			currentIndex--;
			currentState = States.INIT;
			
			Terminals terminal = terminalsMap.get(currentToken);
			if(terminal == null){
				tokenList.add(new IdentToken(currentToken));
			}
			else{
				Operators operator = operatorsMap.get(currentToken);
				if(operator == null){
					tokenList.add(new Token(terminal, null));
				}
				else{
					tokenList.add(new Token(terminal, operator));
				}
			}
		}
		else{
			currentToken += currentChar;
		}
	}

	private void handleLiteralState(char currentChar) {
		if(Character.isDigit(currentChar)){
			currentToken += currentChar;
		}
		else{
			currentIndex--;
			currentState = States.INIT;
			tokenList.add(new LiteralToken(Integer.valueOf(currentToken)));
		}
	}

	private void handleInitState(char currentChar) {
		String s = currentChar + "";
		currentToken = "";
		
		if(s.matches("[0-9]+")){
			currentState = States.LITERAL;
			currentToken += currentChar;
		}
		else if(s.matches("[a-zA-Z]+")){
			currentState = States.IDENT;
			currentToken += currentChar;
		}
		else if(s.matches("[(),;:=*+-/<>&|]+")){
			currentState = States.OPERATOR;
			currentToken += currentChar;
		}
		else if(s.matches("[\t\n\f\r\r]+") || currentChar == ' '){
			// do nothing
		}
		else{
			currentState = States.ERROR;
		}
	}
	
	private void handleErrorState() throws LexicalError{
		throw new LexicalError("Unknown character!", lineNumber);
	}
	
	
	private void initTerminalsMap() {
		terminalsMap.put("(", Terminals.LPAREN);
	    terminalsMap.put(")", Terminals.RPAREN);
	    terminalsMap.put(",", Terminals.COMMA);
	    terminalsMap.put(";", Terminals.SEMICOLON);
	    terminalsMap.put(":", Terminals.COLON);
	    terminalsMap.put(":=", Terminals.BECOMES);
	    terminalsMap.put("*", Terminals.MULTOPR);
	    terminalsMap.put("+", Terminals.ADDOPR);
	    terminalsMap.put("-", Terminals.ADDOPR);
	    terminalsMap.put("=", Terminals.RELOPR);
	    terminalsMap.put("/=", Terminals.RELOPR);
	    terminalsMap.put("<", Terminals.RELOPR);
	    terminalsMap.put(">", Terminals.RELOPR);
	    terminalsMap.put("<=", Terminals.RELOPR);
	    terminalsMap.put(">=", Terminals.RELOPR);
	    terminalsMap.put("&&", Terminals.BOOLOPR);
	    terminalsMap.put("||", Terminals.BOOLOPR);
	    terminalsMap.put("&?", Terminals.BOOLOPR);
	    terminalsMap.put("|?", Terminals.BOOLOPR);
	    terminalsMap.put("bool", Terminals.TYPE);
	    terminalsMap.put("call", Terminals.CALL);
	    terminalsMap.put("const", Terminals.CHANGEMODE);
	    terminalsMap.put("copy", Terminals.MECHMODE);
	    terminalsMap.put("debugin", Terminals.DEBUGIN);
	    terminalsMap.put("debugout", Terminals.DEBUGOUT);
	    terminalsMap.put("divE", Terminals.MULTOPR);
	    terminalsMap.put("do", Terminals.DO);
	    terminalsMap.put("else", Terminals.ELSE);
	    terminalsMap.put("endfun", Terminals.ENDFUN);
	    terminalsMap.put("endif", Terminals.ENDIF);
	    terminalsMap.put("endproc", Terminals.ENDPROC);
	    terminalsMap.put("endprogram", Terminals.ENDPROGRAM);
	    terminalsMap.put("endwhile", Terminals.ENDWHILE);
	    terminalsMap.put("false", Terminals.BOOLLITERAL);
	    terminalsMap.put("fun", Terminals.FUN);
	    terminalsMap.put("global", Terminals.GLOBAL);
	    terminalsMap.put("if", Terminals.IF);
	    terminalsMap.put("in", Terminals.FLOWMODE);
	    terminalsMap.put("init", Terminals.INIT);
	    terminalsMap.put("inout", Terminals.FLOWMODE);
	    terminalsMap.put("int32", Terminals.TYPE );
	    terminalsMap.put("int64", Terminals.TYPE);
	    terminalsMap.put("local", Terminals.LOCAL);
	    terminalsMap.put("modE", Terminals.MULTOPR);
	    terminalsMap.put("not", Terminals.NOTOPER);
	    terminalsMap.put("out", Terminals.FLOWMODE);
	    terminalsMap.put("proc", Terminals.PROC);
	    terminalsMap.put("program", Terminals.PROGRAM);
	    terminalsMap.put("ref", Terminals.MECHMODE);
	    terminalsMap.put("returns", Terminals.RETURNS);
	    terminalsMap.put("skip", Terminals.SKIP);
	    terminalsMap.put("then", Terminals.THEN);
	    terminalsMap.put("true", Terminals.BOOLLITERAL);
	    terminalsMap.put("var", Terminals.CHANGEMODE);
	    terminalsMap.put("while", Terminals.WHILE);
	}
	
	private void initOperatorsMap() {
	    operatorsMap.put("*", Operators.TIMES);
	    operatorsMap.put("+", Operators.PLUS);
	    operatorsMap.put("-", Operators.MINUS);
	    operatorsMap.put("=", Operators.EQ);
	    operatorsMap.put("/=", Operators.NE);
	    operatorsMap.put("<", Operators.LT);
	    operatorsMap.put(">", Operators.GT);
	    operatorsMap.put("<=", Operators.LE);
	    operatorsMap.put(">=", Operators.GE);
	    operatorsMap.put("&?", Operators.CAND);
	    operatorsMap.put("|?", Operators.COR);
	    operatorsMap.put("bool", Operators.BOOL);
	    operatorsMap.put("const", Operators.CONST);
	    operatorsMap.put("copy", Operators.COPY);
	    operatorsMap.put("divE", Operators.DIV_E);
	    operatorsMap.put("false", Operators.FALSE);
	    operatorsMap.put("in", Operators.IN);
	    operatorsMap.put("inout", Operators.INOUT);
	    operatorsMap.put("int32", Operators.INT32 );
	    operatorsMap.put("modE", Operators.MOD_E);
	    operatorsMap.put("out", Operators.OUT);
	    operatorsMap.put("ref", Operators.REF);
	    operatorsMap.put("true", Operators.TRUE);
	    operatorsMap.put("var", Operators.VAR);
	}
}
