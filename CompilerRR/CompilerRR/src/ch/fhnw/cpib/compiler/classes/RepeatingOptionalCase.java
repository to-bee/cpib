package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IBlockCmd;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IRepeatingOptionalCase;
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
	
	
	
}
