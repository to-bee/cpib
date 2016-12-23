package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.scanner.Token;

public class MoniadicOperatorNot implements IConcSyn.IMonadicOperator {

	Token not;

	public MoniadicOperatorNot(Token not) {
		super();
		this.not = not;
	}
	
	
}
