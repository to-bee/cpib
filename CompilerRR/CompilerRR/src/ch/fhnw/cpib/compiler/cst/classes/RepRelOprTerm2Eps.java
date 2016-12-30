package ch.fhnw.cpib.compiler.cst.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;

public class RepRelOprTerm2Eps implements IConcSyn.IRepRELOPRterm2{

	public RepRelOprTerm2Eps() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression toAbs(ITerm2 t2) {
		return t2.toAbs();
	}
}
