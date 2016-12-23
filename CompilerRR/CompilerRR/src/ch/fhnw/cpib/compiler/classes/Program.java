package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IBlockCmd;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IOptionalGlobalDeclarations;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IProgamParameterList;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IProgram;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class Program implements IConcSyn.IProgram {

	Token programToken;
	Token nameToken;
	IConcSyn.IProgamParameterList progList;
	IConcSyn.IOptionalGlobalDeclarations optDec;
	Token doToken;
	IConcSyn.IBlockCmd blockCmd;
	Token endToken;
	
	public Program(Token programToken, Token nameToken, IProgamParameterList progList,
			IOptionalGlobalDeclarations optDec, Token doToken, IBlockCmd blockCmd, Token endToken) {
		super();
		this.programToken = programToken;
		this.nameToken = nameToken;
		this.progList = progList;
		this.optDec = optDec;
		this.doToken = doToken;
		this.blockCmd = blockCmd;
		this.endToken = endToken;
	}
	
	
	public String toString(){
		return null;
		
	}
}
