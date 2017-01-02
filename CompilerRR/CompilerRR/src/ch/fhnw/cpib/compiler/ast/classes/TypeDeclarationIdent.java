package ch.fhnw.cpib.compiler.ast.classes;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ITypeDeclaration;
import ch.fhnw.cpib.compiler.context.CompilerE;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.operators.Type;
import ch.fhnw.cpib.compiler.scanner.tokens.IdentToken;

public class TypeDeclarationIdent implements ITypeDeclaration{

	Token ident;
	private Type type = null;

	public TypeDeclarationIdent(Token ident) {
		super();
		this.ident = ident;
	}

	@Override
	public void check() {
		if (!(ident instanceof IdentToken)) {
			throw new RuntimeException("Wrong ident Type");
		}
		//Check if var is in Current Context
		if (CompilerE.COMPILER.getCurrentContext().getVariable(ident) != null) {
			type = CompilerE.COMPILER.getCurrentContext().getVariable(ident).getType();
		} 
//		//Check if in Global Context
//		if (type == null && CompilerE.COMPILER.getGlobalContext().getVariable(ident) != null) {
//			type = CompilerE.COMPILER.getGlobalContext().getVariable(ident).getType();
//		}
		//if neither -> exception
		if (type == null) {
			throw new RuntimeException("Variable not defined");
		}
		
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public Token getToken() {
		return ident;
	}

	
	

}
