package ch.fhnw.cpib.compiler.cst.classes;

import ch.fhnw.cpib.compiler.ast.classes.OptionalIdentExpression;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IExpressionList;

public class OptionalIdentExprList implements IConcSyn.IOptionalIdent {

	IConcSyn.IExpressionList exprList;

	public OptionalIdentExprList(IExpressionList exprList) {
		super();
		this.exprList = exprList;
	}

	@Override
	public ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IOptionalIdent toAbs() {
		return new OptionalIdentExpression(exprList.toAbs());
	}
	
	 
}
