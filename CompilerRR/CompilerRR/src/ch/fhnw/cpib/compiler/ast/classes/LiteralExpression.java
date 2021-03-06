package ch.fhnw.cpib.compiler.ast.classes;

import javax.management.RuntimeErrorException;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression;
import ch.fhnw.cpib.compiler.context.CompilerE;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Operators;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;
import ch.fhnw.cpib.compiler.scanner.enums.operators.Type;
import ch.fhnw.cpib.compiler.scanner.tokens.LiteralToken;
import ch.fhnw.cpib.compiler.vm.ICodeArray.CodeTooSmallError;
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
		if (literalToken instanceof LiteralToken && literalToken.getTerminal() == Terminals.LITERAL) {
			this.type = Type.INT32;
		} else if (literalToken instanceof LiteralToken && literalToken.getTerminal() == Terminals.BOOLLITERAL) {
			if (literalToken.getOperator() == Operators.TRUE || literalToken.getOperator() == Operators.FALSE) {
				this.type = Type.BOOL;
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
	    	if(literalToken.getOperator() == Operators.TRUE)
	    		carr.put(i, new LoadImInt(1));
	    	else if(literalToken.getOperator() == Operators.FALSE)
	    		carr.put(i, new LoadImInt(0));
	    	else {
	    		throw new RuntimeException("Wrong Operator! Must be True or False.");
	    	}
	    	return i + 1;
	    case INT32:
	      carr.put(i, new LoadImInt(((LiteralToken)literalToken).getValue()));
	      return i + 1;
	    default:
	      break;
	    }
	    throw new RuntimeException("Type of literal unkown: " + this.literalToken);
	}

	@Override
	public void print(String prefix) {
		literalToken.print(prefix);
	}

}
