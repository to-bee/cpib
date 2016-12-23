package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IRepBOOLOPRterm1;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.ITerm1;
import ch.fhnw.cpib.compiler.scanner.Token;

public class RepBoolOprTerm1 implements IConcSyn.IRepBOOLOPRterm1 {

	Token boolopr;
	IConcSyn.ITerm1 term1;
	IConcSyn.IRepBOOLOPRterm1 repBoolOprT1;
	public RepBoolOprTerm1(Token boolopr, ITerm1 term1, IRepBOOLOPRterm1 repBoolOprT1) {
		super();
		this.boolopr = boolopr;
		this.term1 = term1;
		this.repBoolOprT1 = repBoolOprT1;
	}
	
	
}
