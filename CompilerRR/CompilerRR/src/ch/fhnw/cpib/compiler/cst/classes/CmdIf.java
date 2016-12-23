package ch.fhnw.cpib.compiler.cst.classes;

import ch.fhnw.cpib.compiler.ast.classes.CondCmd;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ICommand;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IBlockCmd;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IExpression;
import ch.fhnw.cpib.compiler.scanner.Token;

public class CmdIf implements IConcSyn.ICmd{

	Token ifToken;
	IConcSyn.IExpression ifExpr;
	Token thenToken;
	IConcSyn.IBlockCmd thenBlock;
	Token elseToken;
	IConcSyn.IBlockCmd elseBlock;
	Token endToken;
	public CmdIf(Token ifToken, IExpression ifExpr, Token thenToken, IBlockCmd thenBlock, Token elseToken,
			IBlockCmd elseBlock, Token endToken) {
		super();
		this.ifToken = ifToken;
		this.ifExpr = ifExpr;
		this.thenToken = thenToken;
		this.thenBlock = thenBlock;
		this.elseToken = elseToken;
		this.elseBlock = elseBlock;
		this.endToken = endToken;
	}
	
	@Override
	public ICommand toAbs() {
		return new CondCmd(ifExpr.toAbs(), thenBlock.toAbs(), elseBlock.toAbs());
	}
	
	
}
