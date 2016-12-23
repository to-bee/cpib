package ch.fhnw.cpib.compiler.cst.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.scanner.Token;

public class TypeDeclarationIdent implements IConcSyn.ITypeDeclaration{

	Token ident;

	public TypeDeclarationIdent(Token ident) {
		super();
		this.ident = ident;
	}

	@Override
	public ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ITypeDeclaration toAbs() {
		return new ch.fhnw.cpib.compiler.ast.classes.TypeDeclarationIdent(ident);
	}
	
	
}
