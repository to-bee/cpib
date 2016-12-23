package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IExpression;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IRepeatingOptionalExpressions;
import ch.fhnw.cpib.compiler.scanner.Token;

public class RepeatingOptionalExpressions implements IConcSyn.IRepeatingOptionalExpressions {

	Token comma;
	IConcSyn.IExpression expr;
	IConcSyn.IRepeatingOptionalExpressions repOptExpr;
	public RepeatingOptionalExpressions(Token comma, IExpression expr, IRepeatingOptionalExpressions repOptExpr) {
		super();
		this.comma = comma;
		this.expr = expr;
		this.repOptExpr = repOptExpr;
	}
	
	
}
