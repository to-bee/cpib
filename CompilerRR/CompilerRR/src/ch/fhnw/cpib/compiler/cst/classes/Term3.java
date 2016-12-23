package ch.fhnw.cpib.compiler.cst.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IFactor;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IRepMULTOPRfactor;

public class Term3 implements IConcSyn.ITerm3{

	IConcSyn.IFactor factor;
	IConcSyn.IRepMULTOPRfactor repMulOprFactor;
	public Term3(IFactor factor, IRepMULTOPRfactor repMulOprFactor) {
		super();
		this.factor = factor;
		this.repMulOprFactor = repMulOprFactor;
	}
	@Override
	public ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ITerm3 toAbs() {
		return new  ch.fhnw.cpib.compiler.ast.classes.Term3(factor.toAbs(), repMulOprFactor.toAbs());
	}
	
	
}
