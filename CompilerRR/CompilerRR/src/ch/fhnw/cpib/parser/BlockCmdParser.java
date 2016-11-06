package ch.fhnw.cpib.parser;

import java.util.LinkedList;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class BlockCmdParser extends AbstractParser {

	public BlockCmdParser(LinkedList<Token> tokenlist) {
		super(tokenlist);
	}

	@Override
	public void parse() throws GrammarError {
		if (terminal == Terminals.SWITCH) {
			new CmdParser.parses();
			new RepeatingOptionalCmds.parse();
		} else if (terminal == Terminals.DEBUGOUT) {
			new CmdParser.parses();
			new RepeatingOptionalCmds.parse();
		} else if (terminal == Terminals.DEBUGOUT) {
			new CmdParser.parses();
			new RepeatingOptionalCmds.parse();
		} else if (terminal == Terminals.DEBUGOUT) {
			new CmdParser.parses();
			new RepeatingOptionalCmds.parse();
		} else if (terminal == Terminals.DEBUGOUT) {
			new CmdParser.parses();
			new RepeatingOptionalCmds.parse();
		} else if (terminal == Terminals.DEBUGOUT) {
			new CmdParser.parses();
			new RepeatingOptionalCmds.parse();
		} else if (terminal == Terminals.DEBUGOUT) {
			new CmdParser.parses();
			new RepeatingOptionalCmds.parse();
		} else if (terminal == Terminals.DEBUGOUT) {
			new CmdParser.parses();
			new RepeatingOptionalCmds.parse();
		} else if (terminal == Terminals.DEBUGOUT) {
			new CmdParser.parses();
			new RepeatingOptionalCmds.parse();
		} else if (terminal == Terminals.DEBUGOUT) {
			new CmdParser.parses();
			new RepeatingOptionalCmds.parse();
		} else if (terminal == Terminals.DEBUGOUT) {
			new CmdParser.parses();
			new RepeatingOptionalCmds.parse();
		} else if (terminal == Terminals.DEBUGOUT) {
			new CmdParser.parses();
			new RepeatingOptionalCmds.parse();
		} else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
		
	}
	
	

}
