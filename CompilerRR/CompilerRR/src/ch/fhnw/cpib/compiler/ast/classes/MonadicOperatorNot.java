package ch.fhnw.cpib.compiler.ast.classes;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IMonadicOperator;
import ch.fhnw.cpib.compiler.scanner.Token;

public class MonadicOperatorNot implements IMonadicOperator{
	Token notOpr;

	public MonadicOperatorNot(Token notOpr) {
		super();
		this.notOpr = notOpr;
	}
	
	
}
