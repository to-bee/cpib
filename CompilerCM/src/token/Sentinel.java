package token;

import terminal.Terminals;

public class Sentinel extends BaseToken{
	private final String value;

	public Sentinel() {
		super(Terminals.SENTINEL);
		this.value = "\n";
	}

	String getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "," + value + ")";
	}

}