package ch.fhnw.cpib.compiler.ast.classes;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.operators.Type;
import ch.fhnw.cpib.compiler.scanner.tokens.LiteralToken;

public class LiteralExpression implements IExpression {
	
	/**
	 * Either BoolVal, IntVal
	 */
	private Token literalToken;
	
	private Type type = null;

	public LiteralExpression(Token token) {
	    this.literalToken = token;
	}

	@Override
	public void check() {
		//TODO: Check what?
		if (!(literalToken instanceof LiteralToken)) {
			this.type = Type.INT32;
		} else {
			//TODO: Not sure yet
			System.out.println("LiteralExpression class - Type of Token: " + literalToken.toString());
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

}
