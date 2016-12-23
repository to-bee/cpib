package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IBlockCmd;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IExpression;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IRepeatingOptionalCase;
import ch.fhnw.cpib.compiler.scanner.Token;

public class CmdSwitch implements IConcSyn.ICmd{

	Token switchToken;
	IConcSyn.IExpression expr;
	Token caseToken;
	Token literalToken;
	Token colonToken;
	IConcSyn.IBlockCmd blockCmd;
	IConcSyn.IRepeatingOptionalCase repCase;
	Token caseDefault;
	Token colonToken2;
	IConcSyn.IBlockCmd blockCmd2;
	Token endSwitch;
	
	
	public CmdSwitch(Token switchToken, IExpression expr, Token caseToken, Token literalToken, Token colonToken,
			IBlockCmd blockCmd, IRepeatingOptionalCase repCase, Token caseDefault, Token colonToken2,
			IBlockCmd blockCmd2, Token endSwitch) {
		super();
		this.switchToken = switchToken;
		this.expr = expr;
		this.caseToken = caseToken;
		this.literalToken = literalToken;
		this.colonToken = colonToken;
		this.blockCmd = blockCmd;
		this.repCase = repCase;
		this.caseDefault = caseDefault;
		this.colonToken2 = colonToken2;
		this.blockCmd2 = blockCmd2;
		this.endSwitch = endSwitch;
	}
	
	
}
