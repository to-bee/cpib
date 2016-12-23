package ch.fhnw.cpib.compiler.cst.classes;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.ast.classes.Case;
import ch.fhnw.cpib.compiler.ast.classes.SwitchCmd;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ICommand;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
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


	@Override
	public ICommand toAbs() {
		List<IAbsSyn.ICase> cases = new LinkedList<>();
		cases.add(new Case(literalToken, blockCmd.toAbs()));
		cases.addAll(repCase.toAbs());
		return new SwitchCmd(expr.toAbs(), cases, blockCmd2.toAbs());
	}
	
	
}
