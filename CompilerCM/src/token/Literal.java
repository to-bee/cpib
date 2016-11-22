package token;

import java.math.BigInteger;

import terminal.Terminals;

/**
 * Source: Slides02_ScanningV1, p. 7
 * @author Simon
 *
 */
public class Literal extends BaseToken {
	private final BigInteger value;

	public Literal(BigInteger value) {
		super(Terminals.LITERAL);
		this.value = value;
	}

	BigInteger getValue() {
		return this.value;
	}
	
	@Override
	public String toString() {
		return "(" + super.toString() + "," + value + ")";
	}

}
