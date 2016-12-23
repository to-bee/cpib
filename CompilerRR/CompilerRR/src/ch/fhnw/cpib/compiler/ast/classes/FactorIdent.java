package ch.fhnw.cpib.compiler.ast.classes;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IFactor;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IOptionalIdent;
import ch.fhnw.cpib.compiler.scanner.Token;

public class FactorIdent implements IFactor {
	Token ident;
	IOptionalIdent optionalIdent;

	public FactorIdent(Token ident, IOptionalIdent optionalIdent) {
		super();
		this.ident = ident;
		this.optionalIdent = optionalIdent;
	}

}
