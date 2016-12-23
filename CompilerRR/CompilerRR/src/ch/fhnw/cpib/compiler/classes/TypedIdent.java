package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.ITypeDeclaration;
import ch.fhnw.cpib.compiler.scanner.Token;

public class TypedIdent implements IConcSyn.ITypedIdent{

	Token ident;
	Token colon;
	IConcSyn.ITypeDeclaration typeDec;
	
	
	public TypedIdent(Token ident, Token colon, ITypeDeclaration typeDec) {
		super();
		this.ident = ident;
		this.colon = colon;
		this.typeDec = typeDec;
	}
	
	
}
