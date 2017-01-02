package ch.fhnw.cpib.compiler.ast.classes;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ITypeDeclaration;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ITypedIdent;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.operators.Type;

public class TypedIdent implements ITypedIdent{

	//name
	Token ident;
	
	//Ident or Type
	ITypeDeclaration typeDeclaration;
	
	public TypedIdent(Token ident, ITypeDeclaration typeDeclaration) {
		super();
		this.ident = ident;
		this.typeDeclaration = typeDeclaration;
	}

	@Override
	public void check() {
		//TODO: Check if name is valid.
		typeDeclaration.check();
	}

	@Override
	public Token getToken() {
		return ident;
	}

	@Override
	public Type getType() {
		return typeDeclaration.getType();
	}
	
	

}
