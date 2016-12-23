package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.ICmd;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IRepeatingOptionalCmds;

public class BlockCmd implements IConcSyn.IBlockCmd{

	IConcSyn.ICmd cmd;
	IConcSyn.IRepeatingOptionalCmds repCmds;
	
	public BlockCmd(ICmd cmd, IRepeatingOptionalCmds repCmds) {
		super();
		this.cmd = cmd;
		this.repCmds = repCmds;
	}
	
	
	
}
