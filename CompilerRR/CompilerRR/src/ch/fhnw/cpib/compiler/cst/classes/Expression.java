package ch.fhnw.cpib.compiler.cst.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;

public class Expression implements IConcSyn.IExpression{

	IConcSyn.ITerm1 term1;
	IConcSyn.IRepBOOLOPRterm1 repBoolOpr;
	public Expression(ITerm1 term1, IRepBOOLOPRterm1 repBoolOpr) {
		super();
		this.term1 = term1;
		this.repBoolOpr = repBoolOpr;
	}
	
	@Override
	public ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression toAbs() {
		return this.repBoolOpr.toAbs(this.term1);
	}
	
	
}
