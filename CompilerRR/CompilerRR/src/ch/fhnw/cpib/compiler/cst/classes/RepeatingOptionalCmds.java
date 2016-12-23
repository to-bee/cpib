package ch.fhnw.cpib.compiler.cst.classes;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ICommand;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
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
	@Override
	public List<ICommand> toAbs() {
		List<ICommand> commands = new LinkedList<IAbsSyn.ICommand>();
		commands.add(cmd.toAbs());
		commands.addAll(repCmd.toAbs());
		return commands;
	}
	
	
}
