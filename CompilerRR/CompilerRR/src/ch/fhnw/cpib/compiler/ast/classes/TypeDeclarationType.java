package ch.fhnw.cpib.compiler.ast.classes;

import javax.sql.rowset.spi.TransactionalWriter;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ITypeDeclaration;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;
import ch.fhnw.cpib.compiler.scanner.enums.operators.Type;
import ch.fhnw.cpib.compiler.vm.ICodeArray.CodeTooSmallError;

public class TypeDeclarationType implements ITypeDeclaration{

	Token typeToken;
	private Type type = null;

	public TypeDeclarationType(Token type) {
		super();
		this.typeToken = type;
	}

	@Override
	public void check() {
		if (typeToken.getTerminal() != Terminals.TYPE) {
			throw new RuntimeException("Not a Type");
		}
		switch (typeToken.getOperator()) {
		case BOOL:
			type = Type.BOOL;
			break;
		case INT32:
			type = Type.INT32;
			break;
		default:
			throw new RuntimeException("Unknown Type");
		}
	}
	
	@Override
	public Type getType() {
		return type;
	}

	@Override
	public Token getToken() {
		return typeToken;
	}

	@Override
	public int code(int i) throws CodeTooSmallError {
		return i;
	}
	
	

}
