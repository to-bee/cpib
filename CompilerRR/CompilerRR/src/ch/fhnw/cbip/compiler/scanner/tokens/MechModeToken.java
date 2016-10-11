package ch.fhnw.cbip.compiler.scanner.tokens;

import ch.fhnw.cbip.compiler.scanner.Token;
import ch.fhnw.cbip.compiler.scanner.enums.Terminals;
import ch.fhnw.cbip.compiler.scanner.enums.operators.MechMode;

public class MechModeToken extends Token{

	private final MechMode value;
	
	public MechModeToken(MechMode value) {
		super(Terminals.MECHMODE);
		this.value = value;
	}

	public MechMode getValue() {
		return value;
	}
}
