package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IIdents;
import ch.fhnw.cpib.compiler.scanner.Token;

public class OptionalGlobalInits implements IConcSyn.IOptionalGlobalInits{

	Token initToken;
	IConcSyn.IIdents identsL;
	public OptionalGlobalInits(Token initToken, IIdents identsL) {
		super();
		this.initToken = initToken;
		this.identsL = identsL;
	}
	
	
}
