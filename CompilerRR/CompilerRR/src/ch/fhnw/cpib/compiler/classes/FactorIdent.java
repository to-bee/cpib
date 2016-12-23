package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IOptionalIdent;
import ch.fhnw.cpib.compiler.scanner.Token;

public class FactorIdent implements IConcSyn.IFactor{

	Token ident;
	IConcSyn.IOptionalIdent optIdent;
	public FactorIdent(Token ident, IOptionalIdent optIdent) {
		super();
		this.ident = ident;
		this.optIdent = optIdent;
	}
	
	
}
