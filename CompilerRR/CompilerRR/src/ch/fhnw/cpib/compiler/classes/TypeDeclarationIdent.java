package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.scanner.Token;

public class TypeDeclarationIdent implements IConcSyn.ITypeDeclaration{

	Token ident;

	public TypeDeclarationIdent(Token ident) {
		super();
		this.ident = ident;
	}
	
	
}
