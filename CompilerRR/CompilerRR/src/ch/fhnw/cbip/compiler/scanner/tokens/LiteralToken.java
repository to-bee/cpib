package ch.fhnw.cbip.compiler.scanner.tokens;

import ch.fhnw.cbip.compiler.scanner.Token;
import ch.fhnw.cbip.compiler.scanner.enums.Terminals;

public class LiteralToken extends Token{

	private final int value;
	
	public LiteralToken(int value) {
		super(Terminals.LITERAL, null);
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
	public String toString(){
		return "(" + getTerminal().toString() + ", " +  value  + ")";
	}
	
}
