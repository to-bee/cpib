package ch.fhnw.cpib.compiler.cst.classes;

import ch.fhnw.cpib.compiler.ast.classes.SkipCmd;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ICommand;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.scanner.Token;

public class CmdSkip implements IConcSyn.ICmd{

	Token skipToken;
	
	public CmdSkip(Token t) {
		skipToken = t;
	}

	@Override
	public ICommand toAbs() {
		return new SkipCmd();
	}
	

}
