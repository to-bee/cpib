package ch.fhnw.cpib.compiler.cst.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;

public class Term1 implements IConcSyn.ITerm1{

	IConcSyn.ITerm2 t2;
	IConcSyn.IRepRELOPRterm2 repRelOprT2;
	
	public Term1(ITerm2 t2, IRepRELOPRterm2 repRelOprT2) {
		super();
		this.t2 = t2;
		this.repRelOprT2 = repRelOprT2;
	}

	@Override
	public ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression toAbs() {
		return this.repRelOprT2.toAbs(this.t2);
	}
	
	
}
