package ch.fhnw.cpib.compiler.cst.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.scanner.Token;

public class OptionalMechmode implements IConcSyn.IOptionalMECHMODE{

	Token mechmode;

	public OptionalMechmode(Token mechmode) {
		super();
		this.mechmode = mechmode;
	}

	@Override
	public Token toAbs() {
		return mechmode;
	}
	
}
