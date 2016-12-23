package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IRepBOOLOPRterm1;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.ITerm1;

public class Expression implements IConcSyn.IExpression{

	IConcSyn.ITerm1 term1;
	IConcSyn.IRepBOOLOPRterm1 repBoolOpr;
	public Expression(ITerm1 term1, IRepBOOLOPRterm1 repBoolOpr) {
		super();
		this.term1 = term1;
		this.repBoolOpr = repBoolOpr;
	}
	
	
}
