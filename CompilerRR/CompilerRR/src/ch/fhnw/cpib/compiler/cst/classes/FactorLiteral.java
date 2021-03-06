package ch.fhnw.cpib.compiler.cst.classes;

import ch.fhnw.cpib.compiler.ast.classes.LiteralExpression;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.scanner.Token;

public class FactorLiteral implements IConcSyn.IFactor {

	Token literal;

	public FactorLiteral(Token literal) {
		super();
		this.literal = literal;
	}

	@Override
	public ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression toAbs() {
		return new LiteralExpression(literal);
	}
	
	
}
