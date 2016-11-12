package ch.fhnw.cpib.parser;

import java.util.LinkedList;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class OptionalFlowModeParser extends AbstractParser {

	public OptionalFlowModeParser() {
		super();
	}

	@Override
	public void parse() throws GrammarError {
		if (terminal == Terminals.MECHMODE) {
			// TODO: leer?
		}
		else if (terminal == Terminals.IDENT) {
			// TODO: leer?
		} 
		else if (terminal == Terminals.CHANGEMODE) {
			// TODO: leer?
		}
		else if (terminal == Terminals.FLOWMODE) {
			consume(Terminals.FLOWMODE);
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
		
	}

}
