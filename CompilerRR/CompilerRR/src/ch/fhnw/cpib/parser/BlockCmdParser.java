package ch.fhnw.cpib.parser;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.cst.CSTNode;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class BlockCmdParser extends AbstractParser {

	public BlockCmdParser() {
		super();
	}

	@Override
	public List<CSTNode> parse() throws GrammarError {
		List<CSTNode> list = new LinkedList<CSTNode>();
		if (terminal == Terminals.SWITCH) {
			list.add(new CSTNode("Cmd", new CmdParser().parse()));
			list.add(new CSTNode("RepeatingOptionalCmds", new RepeatingOptionalCmdsParser().parse()));
		} else if (terminal == Terminals.DEBUGOUT) {
			list.add(new CSTNode("Cmd", new CmdParser().parse()));
			list.add(new CSTNode("RepeatingOptionalCmds", new RepeatingOptionalCmdsParser().parse()));
		} else if (terminal == Terminals.DEBUGIN) {
			list.add(new CSTNode("Cmd", new CmdParser().parse()));
			list.add(new CSTNode("RepeatingOptionalCmds", new RepeatingOptionalCmdsParser().parse()));
		} else if (terminal == Terminals.CALL) {
			list.add(new CSTNode("Cmd", new CmdParser().parse()));
			list.add(new CSTNode("RepeatingOptionalCmds", new RepeatingOptionalCmdsParser().parse()));
		} else if (terminal == Terminals.WHILE) {
			list.add(new CSTNode("Cmd", new CmdParser().parse()));
			list.add(new CSTNode("RepeatingOptionalCmds", new RepeatingOptionalCmdsParser().parse()));
		} else if (terminal == Terminals.IF) {
			list.add(new CSTNode("Cmd", new CmdParser().parse()));
			list.add(new CSTNode("RepeatingOptionalCmds", new RepeatingOptionalCmdsParser().parse()));
		} else if (terminal == Terminals.LPAREN) {
			list.add(new CSTNode("Cmd", new CmdParser().parse()));
			list.add(new CSTNode("RepeatingOptionalCmds", new RepeatingOptionalCmdsParser().parse()));
		} else if (terminal == Terminals.ADDOPR) {
			list.add(new CSTNode("Cmd", new CmdParser().parse()));
			list.add(new CSTNode("RepeatingOptionalCmds", new RepeatingOptionalCmdsParser().parse()));
		} else if (terminal == Terminals.NOTOPER) {
			list.add(new CSTNode("Cmd", new CmdParser().parse()));
			list.add(new CSTNode("RepeatingOptionalCmds", new RepeatingOptionalCmdsParser().parse()));
		} else if (terminal == Terminals.IDENT) {
			list.add(new CSTNode("Cmd", new CmdParser().parse()));
			list.add(new CSTNode("RepeatingOptionalCmds", new RepeatingOptionalCmdsParser().parse()));
		} else if (terminal == Terminals.LITERAL) {
			list.add(new CSTNode("Cmd", new CmdParser().parse()));
			list.add(new CSTNode("RepeatingOptionalCmds", new RepeatingOptionalCmdsParser().parse()));
		} else if (terminal == Terminals.SKIP) {
			list.add(new CSTNode("Cmd", new CmdParser().parse()));
			list.add(new CSTNode("RepeatingOptionalCmds", new RepeatingOptionalCmdsParser().parse()));
		} else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString() + " terminal found: " + terminal, 0);
		}
		return list;
	}
	
	

}
