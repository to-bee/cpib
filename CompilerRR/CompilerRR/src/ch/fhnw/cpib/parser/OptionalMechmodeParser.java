package ch.fhnw.cpib.parser;

import java.util.LinkedList;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class OptionalMechmodeParser extends AbstractParser {

	public OptionalMechmodeParser() {
		super();
	}

	@Override
	public void parse() throws GrammarError {
		if (terminal == Terminals.MECHMODE) {
			consume(Terminals.MECHMODE);
		}
		else if (terminal == Terminals.IDENT) {
			// TODO: leer?
		} 
		else if (terminal == Terminals.CHANGEMODE) {
			// TODO: leer?
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
		
	}

}
