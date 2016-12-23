package ch.fhnw.cpib.compiler.classes;

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
	
	
}
