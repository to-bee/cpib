package ch.fhnw.cpib.compiler.cst.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.scanner.Token;

public class OptionalIdentInit implements IConcSyn.IOptionalIdent {

	Token init;

	public OptionalIdentInit(Token init) {
		super();
		this.init = init;
	}

	@Override
	public ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IOptionalIdent toAbs() {
		return new ch.fhnw.cpib.compiler.ast.classes.OptionalIdentInit(init);
	}
	
}
