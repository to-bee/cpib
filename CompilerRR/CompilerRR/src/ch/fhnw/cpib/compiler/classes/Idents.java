package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IRepeatingOptionalIdents;
import ch.fhnw.cpib.compiler.scanner.Token;

public class Idents implements IConcSyn.IIdents{

	Token identToken;
	IConcSyn.IRepeatingOptionalIdents repId;
	public Idents(Token identToken, IRepeatingOptionalIdents repId) {
		super();
		this.identToken = identToken;
		this.repId = repId;
	}
	
	
}
