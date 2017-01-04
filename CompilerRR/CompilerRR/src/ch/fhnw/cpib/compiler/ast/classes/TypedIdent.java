package ch.fhnw.cpib.compiler.ast.classes;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ICommand;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ITypeDeclaration;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ITypedIdent;
import ch.fhnw.cpib.compiler.context.CompilerE;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Operators;
import ch.fhnw.cpib.compiler.scanner.enums.operators.Type;
import ch.fhnw.cpib.compiler.scanner.tokens.LiteralToken;
import ch.fhnw.cpib.compiler.vm.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.compiler.vm.IInstructions;
import ch.fhnw.cpib.compiler.vm.IInstructions.CondJump;
import ch.fhnw.cpib.compiler.vm.IInstructions.UncondJump;

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

	@Override
	public int code(int i) throws CodeTooSmallError {
		int value = 0;
		if(Type.INT32 == getType())
			value = ((LiteralToken) getToken()).getValue();
		else if(getToken().getOperator() == Operators.TRUE)
			value = 1;
		else if(getToken().getOperator() == Operators.FALSE)
			value = 0;
		
		CompilerE.COMPILER.getCodeArray().put(i, new IInstructions.LoadImInt(value));
		return i+1;
	}
	
	

}
