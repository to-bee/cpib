package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.scanner.Token;

public class MoniadicOperatorAddOpr implements IConcSyn.IMonadicOperator{

	Token addOpr;

	public MoniadicOperatorAddOpr(Token addOpr) {
		super();
		this.addOpr = addOpr;
	}
	
	
}
