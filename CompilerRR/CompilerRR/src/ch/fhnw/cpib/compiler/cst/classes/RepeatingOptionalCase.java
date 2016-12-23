package ch.fhnw.cpib.compiler.cst.classes;


import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.ast.classes.Case;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ICase;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.scanner.Token;

public class RepeatingOptionalCase implements IConcSyn.IRepeatingOptionalCase{

	Token caseToken;
	Token literalToken;
	Token colonToken;
	IConcSyn.IBlockCmd blockCmd;
	IConcSyn.IRepeatingOptionalCase repCase;
	
	public RepeatingOptionalCase(Token caseToken, Token literalToken, Token colonToken, IBlockCmd blockCmd,
			IRepeatingOptionalCase repCase) {
		super();
		this.caseToken = caseToken;
		this.literalToken = literalToken;
		this.colonToken = colonToken;
		this.blockCmd = blockCmd;
		this.repCase = repCase;
	}

	@Override
	public List<ICase> toAbs() {
		List<ICase> cases = new LinkedList<IAbsSyn.ICase>();
		cases.add(new Case(literalToken, blockCmd.toAbs()));
		cases.addAll(repCase.toAbs());
		return cases;
	}
	
	
	
}
