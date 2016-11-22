package token;

import enums.Operators;
import terminal.Terminals;

public class Operator extends BaseToken{
	private final Operators value;

	public Operator(Terminals terminal, Operators value) {
		super(terminal);
		this.value = value;
	}

	Operators getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return "(" + super.toString() + "," + value.toString() + ")";
	}
}
