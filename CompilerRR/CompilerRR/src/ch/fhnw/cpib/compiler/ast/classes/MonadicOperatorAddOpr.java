package ch.fhnw.cpib.compiler.ast.classes;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IMonadicOperator;
import ch.fhnw.cpib.compiler.scanner.Token;

public class MonadicOperatorAddOpr implements IMonadicOperator{
	Token addOpr;

	public MonadicOperatorAddOpr(Token addOpr) {
		super();
		this.addOpr = addOpr;
	}

	
	
}
