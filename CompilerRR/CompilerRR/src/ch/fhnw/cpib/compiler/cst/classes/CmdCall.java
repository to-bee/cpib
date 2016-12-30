package ch.fhnw.cpib.compiler.cst.classes;

import ch.fhnw.cpib.compiler.ast.classes.ProcCallCmd;
import ch.fhnw.cpib.compiler.ast.classes.RoutineCall;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ICommand;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.scanner.Token;

public class CmdCall implements IConcSyn.ICmd{
	
	Token callToken;
	Token name;
	IConcSyn.IExpressionList exprList;
	IConcSyn.IOptionalGlobalInits inits;
	
	public CmdCall(Token callToken, Token name, IExpressionList exprList, IOptionalGlobalInits inits) {
		super();
		this.callToken = callToken;
		this.name = name;
		this.exprList = exprList;
		this.inits = inits;
	}
	
	@Override
	public ICommand toAbs() {
		return new ProcCallCmd(new RoutineCall(name, exprList.toAbs()), inits.toAbs());
	}

}
