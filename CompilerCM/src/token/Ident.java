package token;

import terminal.Terminals;

public class Ident extends BaseToken{
	private final String value;

	public Ident(String value) {
		super(Terminals.IDENT);
		this.value = value;
	}

	String getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "," + value + ")";
	}

}