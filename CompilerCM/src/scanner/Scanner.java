package scanner;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import error.LexicalError;
import scanner.token.BaseToken;
import scanner.token.IToken;
import scanner.token.ITokenList;
import scanner.token.IdentToken;
import scanner.token.LiteralToken;
import scanner.token.OperatorToken;
import scanner.token.TokenList;
import scanner.token.DataTypeToken;
import types.Datatypes;
import types.Operators;
import types.Terminals;

public class Scanner {
	private State state = State.STARTSTATE;
	private ITokenList tokenList = new TokenList();

	private int indexCount = 0;

	int numAccu;
	String terminal;
	Dictionary dictionary;

	public Scanner() {
		numAccu = 0;
		terminal = "";
		dictionary = new Dictionary();
	}

	public ITokenList scan(String text) throws LexicalError {
		ITokenList list = analyzeString(text);
		return list;
	}

	private ITokenList analyzeString(String text) throws LexicalError {
		for (indexCount = 0; indexCount < text.length(); indexCount++) {
			char c = text.charAt(indexCount);
			// States
			switch (state) {
			case STARTSTATE:
				handleStartState(c);
				break;
			case IDENTSTATE:
				handleIdentState(c);
				break;
			case LITERALSTATE:
				handleLiteralState(c);
				break;
			case OPERATORSTATE:
				handleOperatorState(c);
				break;
			case ERRORSTATE:
				throw new LexicalError("Character is " + c + ", State is " + state);
			}

			System.out.println("char: " + c + ", state: " + state.toString());
		}
		tokenList.add(new BaseToken(Terminals.SENTINEL));
		return tokenList;
	}

	private void handleStartState(char c) {
		String tmp = Character.toString(c);
		if (Character.isDigit(c)) {
			numAccu = Character.digit(c, 10);
			state = State.LITERALSTATE;
		} else if (Character.isAlphabetic(c)) {
			terminal = tmp;
			state = State.IDENTSTATE;
		} else if (tmp.matches("[!,;:():=<>%/*+-]")) {
			terminal = tmp;
			state = State.OPERATORSTATE;
		} else if (dictionary.matchKeyword(tmp)) {
			tokenList.add(dictionary.getKeyword(tmp));
			state = State.STARTSTATE;
		} else if (tmp.matches("[ \\r\\n\\t]")) {
			// TODO
			// white space, tab, new line, ...
			state = State.STARTSTATE;
		} else
			state = State.ERRORSTATE;
	}

	private void handleIdentState(char c) {
		if (Character.isDigit(c) || Character.isAlphabetic(c)) {
			state = State.IDENTSTATE;
			terminal += c;
		} else {
			// Evaluate terminal
			if (!dictionary.matchKeyword(terminal))
				tokenList.add(new IdentToken(terminal));
			else
				tokenList.add(dictionary.getKeyword(terminal));
			indexCount--;
			state = State.STARTSTATE;
		}
	}

	private void handleLiteralState(char c) throws LexicalError {
		if (Character.isDigit(c)) {
			state = State.LITERALSTATE;
			int digit = Character.digit(c, 10);
			numAccu = numAccu * 10 + digit;
			if (numAccu > Integer.MAX_VALUE) {
				throw new LexicalError("Integer literal too large!");
			}
		} else {
			tokenList.add(new LiteralToken(numAccu));
			indexCount--;
			state = State.STARTSTATE;
		}
	}
	

	private void handleOperatorState(char c) {
		String tmp = Character.toString(c);
		
		/*
		 if(tmp.matches("[,;:()]")){	 
			terminal += c;
			state = State.OPERATORSTATE;
		}
		else if (tmp.matches("[=<>%/*-+!]")) {
			terminal += c;
			state = State.OPERATORSTATE;
		} else { 
		*/
			IToken key = dictionary.getKeyword(terminal);
			IToken op = dictionary.getOperator(terminal);
			if(key == null && op == null){
				state = State.ERRORSTATE;
			} else if(key == null){				
				tokenList.add(op);
			} else 
				tokenList.add(key);
			indexCount--;
			state = State.STARTSTATE;
		//}

	}

	private enum State {
		STARTSTATE, IDENTSTATE, LITERALSTATE, OPERATORSTATE, ERRORSTATE;
	}

}
