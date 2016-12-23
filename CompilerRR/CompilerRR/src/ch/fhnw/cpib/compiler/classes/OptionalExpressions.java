package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IExpression;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IRepeatingOptionalExpressions;

public class OptionalExpressions implements IConcSyn.IOptionalExpressions {

	IConcSyn.IExpression expr;
	IConcSyn.IRepeatingOptionalExpressions repOptExpr;
	public OptionalExpressions(IExpression expr, IRepeatingOptionalExpressions repOptExpr) {
		super();
		this.expr = expr;
		this.repOptExpr = repOptExpr;
	}
	
	
}
