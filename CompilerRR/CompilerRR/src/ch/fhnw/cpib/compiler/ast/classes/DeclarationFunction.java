package ch.fhnw.cpib.compiler.ast.classes;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IDeclaration;

public class DeclarationFunction implements IDeclaration{

	IFunctionDeclaration fd;

	public DeclarationFunction(IFunctionDeclaration fd) {
		super();
		this.fd = fd;
	}
	
	
	
}
