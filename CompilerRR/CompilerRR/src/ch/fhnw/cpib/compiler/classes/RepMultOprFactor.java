package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IFactor;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IRepMULTOPRfactor;
import ch.fhnw.cpib.compiler.scanner.Token;

public class RepMultOprFactor implements IConcSyn.IRepMULTOPRfactor{

	Token multOpr;
	IConcSyn.IFactor factor;
	IConcSyn.IRepMULTOPRfactor repMulOprFactor;
	public RepMultOprFactor(Token multOpr, IFactor factor, IRepMULTOPRfactor repMulOprFactor) {
		super();
		this.multOpr = multOpr;
		this.factor = factor;
		this.repMulOprFactor = repMulOprFactor;
	}
	
	
}
