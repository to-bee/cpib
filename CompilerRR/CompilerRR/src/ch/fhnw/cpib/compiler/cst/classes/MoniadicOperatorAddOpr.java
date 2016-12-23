package ch.fhnw.cpib.compiler.cst.classes;

import ch.fhnw.cpib.compiler.ast.classes.MonadicOperatorAddOpr;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.scanner.Token;

public class MoniadicOperatorAddOpr implements IConcSyn.IMonadicOperator{

	Token addOpr;

	public MoniadicOperatorAddOpr(Token addOpr) {
		super();
		this.addOpr = addOpr;
	}

	@Override
	public ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IMonadicOperator toAbs() {
		return new MonadicOperatorAddOpr(addOpr);
	}
	
	
}
