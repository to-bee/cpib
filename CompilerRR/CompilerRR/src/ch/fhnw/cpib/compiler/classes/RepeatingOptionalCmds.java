package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.ICmd;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IRepeatingOptionalCmds;
import ch.fhnw.cpib.compiler.scanner.Token;

public class RepeatingOptionalCmds implements IConcSyn.IRepeatingOptionalCmds{

	Token semicolonToken;
	IConcSyn.ICmd cmd;
	IConcSyn.IRepeatingOptionalCmds repCmd;
	public RepeatingOptionalCmds(Token semicolonToken, ICmd cmd, IRepeatingOptionalCmds repCmd) {
		super();
		this.semicolonToken = semicolonToken;
		this.cmd = cmd;
		this.repCmd = repCmd;
	}
	
	
}
