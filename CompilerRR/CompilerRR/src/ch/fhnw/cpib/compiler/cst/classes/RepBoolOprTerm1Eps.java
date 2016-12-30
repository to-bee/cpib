package ch.fhnw.cpib.compiler.cst.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;

public class RepBoolOprTerm1Eps implements IConcSyn.IRepBOOLOPRterm1 {

	public RepBoolOprTerm1Eps() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression toAbs(ITerm1 t1) {
		// TODO Auto-generated method stub
		return t1.toAbs();
	}
}
