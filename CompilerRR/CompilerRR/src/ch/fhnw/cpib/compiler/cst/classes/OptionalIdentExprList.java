package ch.fhnw.cpib.compiler.cst.classes;

import ch.fhnw.cpib.compiler.ast.classes.FunCallExpression;
import ch.fhnw.cpib.compiler.ast.classes.RoutineCall;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.scanner.Token;

public class OptionalIdentExprList implements IConcSyn.IOptionalIdent {

	IConcSyn.IExpressionList exprList;

	public OptionalIdentExprList(IExpressionList exprList) {
		super();
		this.exprList = exprList;
	}

	@Override
	public ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression toAbsSyn(Token ident) {
		return new FunCallExpression(new RoutineCall(ident, this.exprList.toAbs()));
	}
	
	 
}
