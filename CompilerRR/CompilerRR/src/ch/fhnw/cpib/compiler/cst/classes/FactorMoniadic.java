package ch.fhnw.cpib.compiler.cst.classes;

import ch.fhnw.cpib.compiler.ast.classes.MonadicExpression;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IFactor;

public class FactorMoniadic implements IConcSyn.IFactor{

	IConcSyn.IMonadicOperator monOpr;
	IConcSyn.IFactor factor;
	
	public FactorMoniadic(IMonadicOperator monOpr, IFactor factor) {
		super();
		this.monOpr = monOpr;
		this.factor = factor;
	}
	
	@Override
	public ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression toAbs() {
		return new MonadicExpression(monOpr.toAbs(), factor.toAbs());
	}
	
	
}
