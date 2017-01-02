package ch.fhnw.cpib.compiler.scanner.tokens;

import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class IdentToken extends Token {

	private final String value;
	
	public IdentToken(String value) {
		super(Terminals.IDENT, null);
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	public String toString(){
		return "(" + getTerminal().toString() + ", " +  value  + ")";
	}
	
	@Override
	public int hashCode() {
		return value.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof IdentToken) {
			return ((IdentToken)obj).value.equals(this.value);
		}
		return false;
	}

	

	
}
