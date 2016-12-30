package ch.fhnw.cpib.compiler.parser;

import ch.fhnw.cpib.compiler.cst.classes.BlockCmd;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class BlockCmdParser extends AbstractParser {

	public BlockCmdParser() {
		super();
	}

	public IConcSyn.IBlockCmd parse() throws GrammarError {
		if (terminal == Terminals.SWITCH) {
			IConcSyn.ICmd cmd = new CmdParser().parse();
			IConcSyn.IRepeatingOptionalCmds repCmds = new RepeatingOptionalCmdsParser().parse();
			return new BlockCmd(cmd, repCmds);
		} else if (terminal == Terminals.DEBUGOUT) {
			IConcSyn.ICmd cmd = new CmdParser().parse();
			IConcSyn.IRepeatingOptionalCmds repCmds = new RepeatingOptionalCmdsParser().parse();
			return new BlockCmd(cmd, repCmds);
		} else if (terminal == Terminals.DEBUGIN) {
			IConcSyn.ICmd cmd = new CmdParser().parse();
			IConcSyn.IRepeatingOptionalCmds repCmds = new RepeatingOptionalCmdsParser().parse();
			return new BlockCmd(cmd, repCmds);
		} else if (terminal == Terminals.CALL) {
			IConcSyn.ICmd cmd = new CmdParser().parse();
			IConcSyn.IRepeatingOptionalCmds repCmds = new RepeatingOptionalCmdsParser().parse();
			return new BlockCmd(cmd, repCmds);
		} else if (terminal == Terminals.WHILE) {
			IConcSyn.ICmd cmd = new CmdParser().parse();
			IConcSyn.IRepeatingOptionalCmds repCmds = new RepeatingOptionalCmdsParser().parse();
			return new BlockCmd(cmd, repCmds);
		} else if (terminal == Terminals.IF) {
			IConcSyn.ICmd cmd = new CmdParser().parse();
			IConcSyn.IRepeatingOptionalCmds repCmds = new RepeatingOptionalCmdsParser().parse();
			return new BlockCmd(cmd, repCmds);
		} else if (terminal == Terminals.LPAREN) {
			IConcSyn.ICmd cmd = new CmdParser().parse();
			IConcSyn.IRepeatingOptionalCmds repCmds = new RepeatingOptionalCmdsParser().parse();
			return new BlockCmd(cmd, repCmds);
		} else if (terminal == Terminals.ADDOPR) {
			IConcSyn.ICmd cmd = new CmdParser().parse();
			IConcSyn.IRepeatingOptionalCmds repCmds = new RepeatingOptionalCmdsParser().parse();
			return new BlockCmd(cmd, repCmds);
		} else if (terminal == Terminals.NOTOPER) {
			IConcSyn.ICmd cmd = new CmdParser().parse();
			IConcSyn.IRepeatingOptionalCmds repCmds = new RepeatingOptionalCmdsParser().parse();
			return new BlockCmd(cmd, repCmds);
		} else if (terminal == Terminals.IDENT) {
			IConcSyn.ICmd cmd = new CmdParser().parse();
			IConcSyn.IRepeatingOptionalCmds repCmds = new RepeatingOptionalCmdsParser().parse();
			return new BlockCmd(cmd, repCmds);
		} else if (terminal == Terminals.LITERAL) {
			IConcSyn.ICmd cmd = new CmdParser().parse();
			IConcSyn.IRepeatingOptionalCmds repCmds = new RepeatingOptionalCmdsParser().parse();
			return new BlockCmd(cmd, repCmds);
		} else if (terminal == Terminals.SKIP) {
			IConcSyn.ICmd cmd = new CmdParser().parse();
			IConcSyn.IRepeatingOptionalCmds repCmds = new RepeatingOptionalCmdsParser().parse();
			return new BlockCmd(cmd, repCmds);
		} else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString() + " terminal found: " + terminal, 0);
		}
	}
	
	

}
