package ch.fhnw.cpib.compiler.cst.classes;

import ch.fhnw.cpib.compiler.ast.classes.AssiCmd;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ICommand;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IExpression;
import ch.fhnw.cpib.compiler.scanner.Token;

public class CmdBecomes implements IConcSyn.ICmd{
	
	IConcSyn.IExpression exprl;
	Token becomesToken;
	IConcSyn.IExpression exprr;
	
	public CmdBecomes(IExpression exprl, Token becomesToken, IExpression exprr) {
		super();
		this.exprl = exprl;
		this.becomesToken = becomesToken;
		this.exprr = exprr;
	}

	@Override
	public ICommand toAbs() {
		return new AssiCmd(exprl.toAbs(), exprr.toAbs());
	}

}
