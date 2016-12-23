package ch.fhnw.cpib.compiler.ast.classes;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IFactor;
import ch.fhnw.cpib.compiler.scanner.Token;

public class FactorLiteral implements IFactor {
	Token literal;

	public FactorLiteral(Token literal) {
		super();
		this.literal = literal;
	}
	
	
	
}
