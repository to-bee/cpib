package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IRepRELOPRterm2;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.ITerm2;

public class Term1 implements IConcSyn.ITerm1{

	IConcSyn.ITerm2 t2;
	IConcSyn.IRepRELOPRterm2 repRelOprT2;
	public Term1(ITerm2 t2, IRepRELOPRterm2 repRelOprT2) {
		super();
		this.t2 = t2;
		this.repRelOprT2 = repRelOprT2;
	}
	
	
}
