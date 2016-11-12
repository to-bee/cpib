package ch.fhnw.cpib.parser;

import java.util.LinkedList;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class GlobalImportParser extends AbstractParser {

	public GlobalImportParser() {
		super();
	}

	@Override
	public void parse() throws GrammarError {
		if (terminal == Terminals.IDENT) {
			new OptionalFlowModeParser().parse();
			new OptionalChangemodeParser().parse();
			consume(Terminals.IDENT);
		} 
		else if (terminal == Terminals.CHANGEMODE) {
			new OptionalFlowModeParser().parse();
			new OptionalChangemodeParser().parse();
			consume(Terminals.IDENT);
		} 
		else if (terminal == Terminals.FLOWMODE) {
			new OptionalFlowModeParser().parse();
			new OptionalChangemodeParser().parse();
			consume(Terminals.IDENT);
		} 
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
		
	}

}
