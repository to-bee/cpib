package ch.fhnw.cpib.parser;

import java.util.LinkedList;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class BlockCmdParser extends AbstractParser {

	public BlockCmdParser() {
		super();
	}

	@Override
	public void parse() throws GrammarError {
		if (terminal == Terminals.SWITCH) {
			new CmdParser().parse();
			new RepeatingOptionalCmdsParser().parse();
		} else if (terminal == Terminals.DEBUGOUT) {
			new CmdParser().parse();
			new RepeatingOptionalCmdsParser().parse();
		} else if (terminal == Terminals.DEBUGIN) {
			new CmdParser().parse();
			new RepeatingOptionalCmdsParser().parse();
		} else if (terminal == Terminals.CALL) {
			new CmdParser().parse();
			new RepeatingOptionalCmdsParser().parse();
		} else if (terminal == Terminals.WHILE) {
			new CmdParser().parse();
			new RepeatingOptionalCmdsParser().parse();
		} else if (terminal == Terminals.IF) {
			new CmdParser().parse();
			new RepeatingOptionalCmdsParser().parse();
		} else if (terminal == Terminals.LPAREN) {
			new CmdParser().parse();
			new RepeatingOptionalCmdsParser().parse();
		} else if (terminal == Terminals.ADDOPR) {
			new CmdParser().parse();
			new RepeatingOptionalCmdsParser().parse();
		} else if (terminal == Terminals.NOTOPER) {
			new CmdParser().parse();
			new RepeatingOptionalCmdsParser().parse();
		} else if (terminal == Terminals.IDENT) {
			new CmdParser().parse();
			new RepeatingOptionalCmdsParser().parse();
		} else if (terminal == Terminals.LITERAL) {
			new CmdParser().parse();
			new RepeatingOptionalCmdsParser().parse();
		} else if (terminal == Terminals.SKIP) {
			new CmdParser().parse();
			new RepeatingOptionalCmdsParser().parse();
		} else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString() + " terminal found: " + terminal, 0);
		}
		
	}
	
	

}
