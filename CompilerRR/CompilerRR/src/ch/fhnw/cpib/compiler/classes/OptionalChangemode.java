package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.scanner.Token;

public class OptionalChangemode implements IConcSyn.IOptionalCHANGEMODE{

	Token changemode;

	public OptionalChangemode(Token changemode) {
		super();
		this.changemode = changemode;
	}
	
	
}
