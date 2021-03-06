package ch.fhnw.cpib.compiler.cst.classes;

import ch.fhnw.cpib.compiler.ast.classes.InputCmd;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ICommand;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IExpression;
import ch.fhnw.cpib.compiler.scanner.Token;

public class CmdDebugIn implements IConcSyn.ICmd{

	Token debugIn;
	IConcSyn.IExpression expr;
	
	
	public CmdDebugIn(Token debugIn, IExpression expr) {
		super();
		this.debugIn = debugIn;
		this.expr = expr;
	}


	@Override
	public ICommand toAbs() {
		return new InputCmd(expr.toAbs());
	}
	
	
}
