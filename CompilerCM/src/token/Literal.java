package token;

import terminal.Terminals;

/**
 * Source: Slides02_ScanningV1, p. 7
 * @author Simon
 *
 */
public class Literal extends BaseToken {
	private final int value;

	public Literal(int value) {
		super(Terminals.LITERAL);
		this.value = value;
	}

	int getValue() {
		return this.value;
	}
	
	@Override
	public String toString() {
		return "(" + super.toString() + "," + value + ")";
	}

}
