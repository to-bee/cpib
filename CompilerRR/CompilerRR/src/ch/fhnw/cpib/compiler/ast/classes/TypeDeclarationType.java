package ch.fhnw.cpib.compiler.ast.classes;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ITypeDeclaration;
import ch.fhnw.cpib.compiler.scanner.Token;

public class TypeDeclarationType implements ITypeDeclaration{

	Token type;

	public TypeDeclarationType(Token type) {
		super();
		this.type = type;
	}
	

	
	

}
