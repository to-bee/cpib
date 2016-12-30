package ch.fhnw.cpib.compiler.ast.classes;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ITypeDeclaration;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ITypedIdent;
import ch.fhnw.cpib.compiler.scanner.Token;

public class TypedIdent implements ITypedIdent{

	Token ident;
	ITypeDeclaration typeDeclaration;
	
	public TypedIdent(Token ident, ITypeDeclaration typeDeclaration) {
		super();
		this.ident = ident;
		this.typeDeclaration = typeDeclaration;
	}

	@Override
	public void check() {
		// TODO Auto-generated method stub
		
	}
	
	

}
