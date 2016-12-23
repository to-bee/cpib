package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IBlockCmd;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IOptionalGlobalImports;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IOptionalLocalStorageDeclarations;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IParameterList;
import ch.fhnw.cpib.compiler.scanner.Token;

public class ProcedureDeclaration implements IConcSyn.IProcedureDeclaration {

	Token procToken;
	Token ident;
	IConcSyn.IParameterList parList;
	IConcSyn.IOptionalGlobalImports optGloImp;
	IConcSyn.IOptionalLocalStorageDeclarations optLocStoDec;
	Token doToken;
	IConcSyn.IBlockCmd blockCmd;
	Token endProc;
	public ProcedureDeclaration(Token procToken, Token ident, IParameterList parList, IOptionalGlobalImports optGloImp,
			IOptionalLocalStorageDeclarations optLocStoDec, Token doToken, IBlockCmd blockCmd, Token endProc) {
		super();
		this.procToken = procToken;
		this.ident = ident;
		this.parList = parList;
		this.optGloImp = optGloImp;
		this.optLocStoDec = optLocStoDec;
		this.doToken = doToken;
		this.blockCmd = blockCmd;
		this.endProc = endProc;
	}
	
	
}
