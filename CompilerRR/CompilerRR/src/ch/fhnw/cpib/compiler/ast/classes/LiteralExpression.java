package ch.fhnw.cpib.compiler.ast.classes;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression;
import ch.fhnw.cpib.compiler.context.CompilerE;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Operators;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;
import ch.fhnw.cpib.compiler.scanner.enums.operators.Type;
import ch.fhnw.cpib.compiler.scanner.tokens.LiteralToken;
import ch.fhnw.cpib.compiler.vm.ICodeArray;
import ch.fhnw.cpib.compiler.vm.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.compiler.vm.IInstructions;
import ch.fhnw.cpib.compiler.vm.*;
import ch.fhnw.cpib.compiler.vm.IInstructions.*;

public class LiteralExpression implements IExpression {
	
	/**
	 * Either BOOLLITERAL with Operator true,false, or literalToken with value
	 */
	private Token literalToken;
	
	private Type type = null;

	public LiteralExpression(Token token) {
	    this.literalToken = token;
	}

	@Override
	public void check() {
		//TODO: Check what?
		if (literalToken instanceof LiteralToken) {
			this.type = Type.INT32;
		} else if (literalToken.getTerminal() == Terminals.BOOLLITERAL) {
			if (literalToken.getOperator() == Operators.TRUE || literalToken.getOperator() == Operators.FALSE) {
				type = Type.BOOL;
			}
		} 
		if (type == null) {
			throw new RuntimeException("Could not determine type of literaltoken");
		}
	}

	@Override
	public Token getToken() {
		return this.literalToken;
	}

	@Override
	public Type getType() {
		return this.type;
	}

	@Override
	public int code(int i) throws CodeTooSmallError {
		final ICodeArray carr = CompilerE.COMPILER.getCodeArray();

	    switch (this.type) {
	    case BOOL:
	    case INT32:
	      carr.put(i, new LoadImInt(((LiteralToken)literalToken).getValue()));
	      return i + 1;
	    default:
	      break;
	    }
	    throw new RuntimeException("Type of literal unkown: " + this.literalToken);
	}

}
