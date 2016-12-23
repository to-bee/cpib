package ch.fhnw.cpib.compiler.classes;

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
	
	
}
