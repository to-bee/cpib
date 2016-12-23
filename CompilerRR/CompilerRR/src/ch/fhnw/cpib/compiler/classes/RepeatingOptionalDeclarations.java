package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IDeclaration;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IRepeatingOptionalDeclarations;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IStorageDeclaration;
import ch.fhnw.cpib.compiler.scanner.Token;

public class RepeatingOptionalDeclarations implements IConcSyn.IRepeatingOptionalDeclarations{

	Token semicolon;
	IConcSyn.IDeclaration stoDecl;
	IConcSyn.IRepeatingOptionalDeclarations repOptDecl;
	public RepeatingOptionalDeclarations(Token semicolon, IDeclaration decl,
			IRepeatingOptionalDeclarations repOptDecl) {
		super();
		this.semicolon = semicolon;
		this.stoDecl = decl;
		this.repOptDecl = repOptDecl;
	}
	
	
}
