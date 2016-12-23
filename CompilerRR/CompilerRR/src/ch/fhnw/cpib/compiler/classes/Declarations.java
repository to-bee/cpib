package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IDeclaration;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IRepeatingOptionalDeclarations;

public class Declarations implements IConcSyn.IDeclarations{

	IConcSyn.IDeclaration decl;
	IConcSyn.IRepeatingOptionalDeclarations repOptDecl;
	public Declarations(IDeclaration decl, IRepeatingOptionalDeclarations repOptDecl) {
		super();
		this.decl = decl;
		this.repOptDecl = repOptDecl;
	}
	
	
	
}
