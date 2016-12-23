package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IRepADDOPRterm3;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.ITerm3;
import ch.fhnw.cpib.compiler.scanner.Token;

public class Term2 implements IConcSyn.ITerm2{

	IConcSyn.ITerm3 term3;
	IConcSyn.IRepADDOPRterm3 repAddOprT3;
	public Term2(ITerm3 t3, IRepADDOPRterm3 repAddOprT3) {
		super();
		this.term3 = t3;
		this.repAddOprT3 = repAddOprT3;
	}
	
	
}
