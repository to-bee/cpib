package ch.fhnw.cpib.compiler.ast.classes;

import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IFactor;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IMULTOPRfactor;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ITerm3;

public class Term3 implements ITerm3 {
	IFactor factor;
	List<IMULTOPRfactor> multOprFactorList;

	public Term3(IFactor factor, List<IMULTOPRfactor> multOprFactorList) {
		super();
		this.factor = factor;
		this.multOprFactorList = multOprFactorList;
	}

}
