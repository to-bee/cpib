package ch.fhnw.cpib.compiler.scanner;

import ch.fhnw.cpib.compiler.scanner.enums.Operators;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class Token {

	private final Terminals terminal;
	private final Operators operator;
	
	public Token(Terminals t, Operators o) {
		terminal = t;
		operator = o;
	}

	public Terminals getTerminal() {
		return terminal;
	}
	
	public Operators getOperator() {
		return operator;
	}

	@Override
	public String toString() {
		if (operator == null) {
			return "(" + terminal.toString() + ")";
		} else {
			return "(" + terminal.toString() + ", " +  operator.toString()  + ")";
		}
	}

}

