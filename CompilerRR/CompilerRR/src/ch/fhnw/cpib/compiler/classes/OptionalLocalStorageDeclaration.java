package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IRepeatingOptionalDeclarations;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IRepeatingOptionalStorageDeclarations;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IStorageDeclaration;
import ch.fhnw.cpib.compiler.scanner.Token;

public class OptionalLocalStorageDeclaration implements IConcSyn.IOptionalLocalStorageDeclarations{

	Token local;
	IConcSyn.IStorageDeclaration stoDecl;
	IConcSyn.IRepeatingOptionalStorageDeclarations repOptDecl;
	public OptionalLocalStorageDeclaration(Token local, IStorageDeclaration stoDecl,
			IRepeatingOptionalStorageDeclarations repOptStoDecl) {
		super();
		this.local = local;
		this.stoDecl = stoDecl;
		this.repOptDecl = repOptStoDecl;
	}
	
	
}
