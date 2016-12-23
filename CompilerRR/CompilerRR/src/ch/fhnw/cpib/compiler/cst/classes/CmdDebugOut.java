package ch.fhnw.cpib.compiler.cst.classes;

import ch.fhnw.cpib.compiler.ast.classes.OutputCmd;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ICommand;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.scanner.Token;

public class CmdDebugOut implements IConcSyn.ICmd{

	Token debugOut;
	IConcSyn.IExpression expr;
	public CmdDebugOut(Token debugOut, IExpression expr) {
		super();
		this.debugOut = debugOut;
		this.expr = expr;
	}
	@Override
	public ICommand toAbs() {
		return new OutputCmd(expr.toAbs());
	}
	
	
	
}
