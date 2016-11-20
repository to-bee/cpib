package ch.fhnw.cpib.parser;

import java.util.LinkedList;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class IdentsParser extends AbstractParser {

	public IdentsParser() {
		super();
	}
	
	public void parse() throws GrammarError {
		if(terminal == Terminals.IDENT){
			consume(Terminals.IDENT);
			new RepeatingOptionalIdentsParser().parse();
		}
		else {
			System.out.println(tokenlist.toString());
			throw new GrammarError("GrammarError at: "+ this.getClass().toString() + " terminal found: " + terminal, 0);
		}
	}
	
}
