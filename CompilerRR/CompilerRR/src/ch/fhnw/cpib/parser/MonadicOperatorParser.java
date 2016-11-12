package ch.fhnw.cpib.parser;

import java.util.LinkedList;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class MonadicOperatorParser extends AbstractParser {

	public MonadicOperatorParser() {
		super();
	}
	
	public void parse() throws GrammarError {
		if (terminal == Terminals.NOTOPER) {
			consume(Terminals.NOTOPER);
		} 
		else if(terminal == Terminals.ADDOPR){
			consume(Terminals.ADDOPR);
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
	}
	
}
