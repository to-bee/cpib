package ch.fhnw.cpib.parser;

import java.util.LinkedList;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class OptionalGlobalDeclarationsParser extends AbstractParser {

	public OptionalGlobalDeclarationsParser(LinkedList<Token> tokenlist) {
		super(tokenlist);
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
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
		
	}

}
