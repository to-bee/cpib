package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.scanner.Token;

public class OptionalIdentInit implements IConcSyn.IOptionalIdent {

	Token init;

	public OptionalIdentInit(Token init) {
		super();
		this.init = init;
	}
	
}
