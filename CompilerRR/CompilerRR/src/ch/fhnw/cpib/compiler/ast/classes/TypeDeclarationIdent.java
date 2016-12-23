package ch.fhnw.cpib.compiler.ast.classes;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ITypeDeclaration;
import ch.fhnw.cpib.compiler.scanner.Token;

public class TypeDeclarationIdent implements ITypeDeclaration{

	Token ident;

	public TypeDeclarationIdent(Token ident) {
		super();
		this.ident = ident;
	}

	
	

}
