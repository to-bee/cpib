package ch.fhnw.cpib.compiler.ast.classes;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IOptionalIdent;
import ch.fhnw.cpib.compiler.scanner.Token;

public class OptionalIdentInit implements IOptionalIdent{
	Token init;

	public OptionalIdentInit(Token init) {
		super();
		this.init = init;
	}
	
	
}
