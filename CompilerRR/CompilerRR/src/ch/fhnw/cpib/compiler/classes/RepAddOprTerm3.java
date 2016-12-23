package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IRepADDOPRterm3;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.ITerm3;
import ch.fhnw.cpib.compiler.scanner.Token;

public class RepAddOprTerm3 implements IConcSyn.IRepADDOPRterm3 {

	Token addOpr;
	IConcSyn.ITerm3 t3;
	IConcSyn.IRepADDOPRterm3 repAddOprT3;
	public RepAddOprTerm3(Token addOpr, ITerm3 t3, IRepADDOPRterm3 repAddOprT3) {
		super();
		this.addOpr = addOpr;
		this.t3 = t3;
		this.repAddOprT3 = repAddOprT3;
	}
	
	
}
