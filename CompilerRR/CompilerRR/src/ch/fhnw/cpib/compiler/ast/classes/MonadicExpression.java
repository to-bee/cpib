package ch.fhnw.cpib.compiler.ast.classes;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;
import ch.fhnw.cpib.compiler.scanner.enums.operators.Type;

public class MonadicExpression implements IExpression{

	Token operator;
	IExpression expr;
	Type type = null;
	
	public MonadicExpression(Token operator, IExpression expr) {
		super();
		this.operator = operator;
		this.expr = expr;
	}

	@Override
	public void check() {
		this.expr.check();
	}

	@Override
	public Token getToken() {
		return this.operator;
	}

	@Override
	public Type getType() {
		if (this.operator.getTerminal() == Terminals.NOTOPER) this.type = Type.BOOL;
	    else this.type = Type.INT32; // ADDOPR
		return type;
	}

}
