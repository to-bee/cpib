package ch.fhnw.cpib.compiler.cst.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
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
	
	@Override
	public ch.fhnw.cpib.compiler.ast.classes.ProcedureDeclaration toAbs() {
		return new ch.fhnw.cpib.compiler.ast.classes.ProcedureDeclaration(
				ident, parList.toAbs(), optGloImp.toAbs(), optLocStoDec.toAbs(), blockCmd.toAbs());
	}
	
	
	
	
}
