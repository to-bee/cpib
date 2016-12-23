package ch.fhnw.cpib.compiler.cst.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IExpression;
import ch.fhnw.cpib.compiler.scanner.Token;

public class FactorExpression implements IConcSyn.IFactor {

	Token lpar;
	IConcSyn.IExpression expr;
	Token rpar;
	public FactorExpression(Token lpar, IExpression expr, Token rpar) {
		super();
		this.lpar = lpar;
		this.expr = expr;
		this.rpar = rpar;
	}
	
	@Override
	public ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IFactor toAbs() {
		return new ch.fhnw.cpib.compiler.ast.classes.FactorExpression(expr.toAbs());
	}
	
	
}
