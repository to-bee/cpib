package ch.fhnw.cpib.compiler.ast.classes;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IDeclaration;

public class DeclarationProcedure implements IDeclaration{

	IProcedureDeclaration pd;

	public DeclarationProcedure(IProcedureDeclaration pd) {
		super();
		this.pd = pd;
	}
	
	
	
}
