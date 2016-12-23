package ch.fhnw.cpib.compiler.cst.classes;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;

public class OptionalExpressions implements IConcSyn.IOptionalExpressions {

	IConcSyn.IExpression expr;
	IConcSyn.IRepeatingOptionalExpressions repOptExpr;
	public OptionalExpressions(IExpression expr, IRepeatingOptionalExpressions repOptExpr) {
		super();
		this.expr = expr;
		this.repOptExpr = repOptExpr;
	}
	@Override
	public List<ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression> toAbs() {
		List<ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression>
		expressions = new LinkedList<IAbsSyn.IExpression>();
		expressions.add(expr.toAbs());
		expressions.addAll(repOptExpr.toAbs());
		return expressions;
	}
	
	
}
