package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IRepeatingOptionalStorageDeclarations;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IStorageDeclaration;
import ch.fhnw.cpib.compiler.scanner.Token;

public class RepeatingOptionalStorageDeclarations implements IConcSyn.IRepeatingOptionalStorageDeclarations {

	Token semicolon;
	IConcSyn.IStorageDeclaration stoDecl;
	IConcSyn.IRepeatingOptionalStorageDeclarations repOptStoDecl;
	public RepeatingOptionalStorageDeclarations(Token semicolon, IStorageDeclaration stoDecl,
			IRepeatingOptionalStorageDeclarations repOptStoDecl) {
		super();
		this.semicolon = semicolon;
		this.stoDecl = stoDecl;
		this.repOptStoDecl = repOptStoDecl;
	}
	
	
}
