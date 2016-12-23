package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IExpressionList;

public class OptionalIdentExprList implements IConcSyn.IOptionalIdent {

	IConcSyn.IExpressionList exprList;

	public OptionalIdentExprList(IExpressionList exprList) {
		super();
		this.exprList = exprList;
	}
	
	 
}
