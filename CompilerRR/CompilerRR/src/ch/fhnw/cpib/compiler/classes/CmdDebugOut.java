package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IExpression;
import ch.fhnw.cpib.compiler.scanner.Token;

public class CmdDebugOut implements IConcSyn.ICmd{

	Token debugOut;
	IConcSyn.IExpression expr;
	public CmdDebugOut(Token debugOut, IExpression expr) {
		super();
		this.debugOut = debugOut;
		this.expr = expr;
	}
	
	
	
}
