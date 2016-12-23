package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IBlockCmd;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IOptionalGlobalImports;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IOptionalLocalStorageDeclarations;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IParameterList;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IStorageDeclaration;
import ch.fhnw.cpib.compiler.scanner.Token;

public class FunctionDeclaration implements IConcSyn.IFunctionDeclaration{

	Token func;
	Token ident;
	IConcSyn.IParameterList parList;
	Token returns;
	IConcSyn.IStorageDeclaration stoDec;
	IConcSyn.IOptionalGlobalImports optGlobImp;
	IConcSyn.IOptionalLocalStorageDeclarations optLocStorDec;
	Token doToken;
	IConcSyn.IBlockCmd blockCmd;
	Token endFunToken;
	public FunctionDeclaration(Token func, Token ident, IParameterList parList, Token returns,
			IStorageDeclaration stoDec, IOptionalGlobalImports optGlobImp,
			IOptionalLocalStorageDeclarations optLocStorDec, Token doToken, IBlockCmd blockCmd, Token endFunToken) {
		super();
		this.func = func;
		this.ident = ident;
		this.parList = parList;
		this.returns = returns;
		this.stoDec = stoDec;
		this.optGlobImp = optGlobImp;
		this.optLocStorDec = optLocStorDec;
		this.doToken = doToken;
		this.blockCmd = blockCmd;
		this.endFunToken = endFunToken;
	}
	
	
	
}
