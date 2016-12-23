package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IRepRELOPRterm2;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.ITerm2;
import ch.fhnw.cpib.compiler.scanner.Token;

public class RepRelOprTerm2 implements IConcSyn.IRepRELOPRterm2 {

	Token relOpr;
	IConcSyn.ITerm2 t2;
	IConcSyn.IRepRELOPRterm2 repRelOprT2;
	public RepRelOprTerm2(Token relOpr, ITerm2 t2, IRepRELOPRterm2 repRelOprT2) {
		super();
		this.relOpr = relOpr;
		this.t2 = t2;
		this.repRelOprT2 = repRelOprT2;
	}
	
	
	
}

