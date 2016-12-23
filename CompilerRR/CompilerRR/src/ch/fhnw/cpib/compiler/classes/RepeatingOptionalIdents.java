package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IIdents;
import ch.fhnw.cpib.compiler.scanner.Token;

public class RepeatingOptionalIdents implements IConcSyn.IRepeatingOptionalIdents{

	Token commaToken;
	Token ident;
	IConcSyn.IIdents idents;
	public RepeatingOptionalIdents(Token commaToken, Token ident, IIdents idents) {
		super();
		this.commaToken = commaToken;
		this.ident = ident;
		this.idents = idents;
	}
	
	
	
}
