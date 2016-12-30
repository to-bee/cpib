package ch.fhnw.cpib.compiler.cst.classes;

import ch.fhnw.cpib.compiler.ast.classes.StoreExpression;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.scanner.Token;

public class OptionalIdentEps implements IConcSyn.IOptionalIdent {

	public OptionalIdentEps() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression toAbsSyn(Token ident) {
		return new StoreExpression(ident, false);
	}
}
