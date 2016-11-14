package ch.fhnw.cpib.parser;

import java.util.LinkedList;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class OptionalChangemodeParser extends AbstractParser {

	public OptionalChangemodeParser() {
		super();
	}

	@Override
	public void parse() throws GrammarError {
		if (terminal == Terminals.IDENT) {
			// TODO: leer?
		} 
		else if (terminal == Terminals.CHANGEMODE) {
			consume(Terminals.CHANGEMODE);
		}
		else {
			System.out.println(tokenlist.toString());
			System.out.println(token);
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
		
	}

}
