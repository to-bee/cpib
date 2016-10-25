package scanner.token;

import types.Terminals;

public class IdentToken extends BaseToken{
	private final String value;

	public IdentToken(String value) {
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