package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IExpressionList;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IOptionalGlobalInits;
import ch.fhnw.cpib.compiler.scanner.Token;

public class CmdCall implements IConcSyn.ICmd{
	
	Token callToken;
	public CmdCall(Token callToken, Token name, IExpressionList exprList, IOptionalGlobalInits inits) {
		super();
		this.callToken = callToken;
		this.name = name;
		this.exprList = exprList;
		this.inits = inits;
	}
	
	Token name;
	IConcSyn.IExpressionList exprList;
	IConcSyn.IOptionalGlobalInits inits;

	

}
