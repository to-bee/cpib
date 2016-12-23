package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IBlockCmd;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IExpression;
import ch.fhnw.cpib.compiler.scanner.Token;

public class CmdWhile implements IConcSyn.ICmd {

	 //WHILE <expression> DO <blockCmd> ENDWHILE
	 Token whileToken;
	 IConcSyn.IExpression expr;
	 Token doToken;
	 IConcSyn.IBlockCmd blockCmd;
	 Token endWihle;
	public CmdWhile(Token whileToken, IExpression expr, Token doToken, IBlockCmd blockCmd, Token endWihle) {
		super();
		this.whileToken = whileToken;
		this.expr = expr;
		this.doToken = doToken;
		this.blockCmd = blockCmd;
		this.endWihle = endWihle;
	}
	 
	 
	 
}
