package ch.fhnw.cpib.parser;

import java.util.LinkedList;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class OptionalGlobalDeclarationsParser extends AbstractParser {

	public OptionalGlobalDeclarationsParser() {
		super();
	}

	@Override
	public void parse() throws GrammarError {
		if (terminal == Terminals.DO) {
			// TODO: leer?
		}
		else if (terminal == Terminals.GLOBAL) {
			consume(Terminals.GLOBAL);
			new DeclarationsParser().parse();
		} 
		else {
			System.out.println(tokenlist.toString());
			throw new GrammarError("GrammarError at: "+ this.getClass().toString() + " terminal found: " + terminal, 0);
		}
		
	}

}
