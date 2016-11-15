package scanner.token;

import types.Operators;
import types.Terminals;

public class OperatorToken extends BaseToken{
	private final Operators value;

	public OperatorToken(Terminals terminal, Operators value) {
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
