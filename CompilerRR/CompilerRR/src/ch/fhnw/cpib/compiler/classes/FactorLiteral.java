package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.scanner.Token;

public class FactorLiteral implements IConcSyn.IFactor {

	Token literal;

	public FactorLiteral(Token literal) {
		super();
		this.literal = literal;
	}
	
	
}
