package ch.fhnw.cpib.compiler.cst.classes;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ICommand;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;

public class BlockCmd implements IConcSyn.IBlockCmd{

	IConcSyn.ICmd cmd;
	IConcSyn.IRepeatingOptionalCmds repCmds;
	
	public BlockCmd(ICmd cmd, IRepeatingOptionalCmds repCmds) {
		super();
		this.cmd = cmd;
		this.repCmds = repCmds;
	}

	@Override
	public List<ICommand> toAbs() {
		List<ICommand> commands = new LinkedList<>();
		commands.add(cmd.toAbs());
		commands.addAll(repCmds.toAbs());
		return commands;
	}

}

