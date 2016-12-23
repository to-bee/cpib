package ch.fhnw.cpib.compiler.cst.classes;

import ch.fhnw.cpib.compiler.ast.classes.FactorMonadicOperator;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IFactor;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IMonadicOperator;

public class FactorMoniadic implements IConcSyn.IFactor{

	IConcSyn.IMonadicOperator monOpr;
	IConcSyn.IFactor factor;
	public FactorMoniadic(IMonadicOperator monOpr, IFactor factor) {
		super();
		this.monOpr = monOpr;
		this.factor = factor;
	}
	@Override
	public ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IFactor toAbs() {
		return new FactorMonadicOperator(monOpr.toAbs(), factor.toAbs());
	}
	
	
}
