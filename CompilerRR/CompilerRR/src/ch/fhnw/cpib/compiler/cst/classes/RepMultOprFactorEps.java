package ch.fhnw.cpib.compiler.cst.classes;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IMULTOPRfactor;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;

public class RepMultOprFactorEps implements IConcSyn.IRepMULTOPRfactor {

	public RepMultOprFactorEps() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression toAbs(IFactor factor) {
		return factor.toAbs();
	}
}
