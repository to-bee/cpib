package ch.fhnw.cpib.compiler.ast.classes;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IFactor;

public class FactorExpression implements IFactor {
	IExpression expression;

	public FactorExpression(IExpression expression) {
		super();
		this.expression = expression;
	}

}
