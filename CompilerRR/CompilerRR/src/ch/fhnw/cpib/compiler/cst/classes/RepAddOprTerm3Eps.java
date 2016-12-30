package ch.fhnw.cpib.compiler.cst.classes;


import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;

public class RepAddOprTerm3Eps implements IConcSyn.IRepADDOPRterm3{

	public RepAddOprTerm3Eps() {
		// TODO Auto-generated constructor stub
	}



	@Override
	public ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression toAbs(
			ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression dyadicExpr) {
	
		return dyadicExpr;
	}
}
