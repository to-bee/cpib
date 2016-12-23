package ch.fhnw.cpib.compiler.cst.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.scanner.Token;

public class OptionalFlowmodeEps implements IConcSyn.IOptionalFLOWMODE{

	public OptionalFlowmodeEps() {
	}

	@Override
	public Token toAbs() {
		return null;
	}
}
