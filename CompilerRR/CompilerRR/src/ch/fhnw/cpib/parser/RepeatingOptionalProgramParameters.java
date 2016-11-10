package ch.fhnw.cpib.parser;

import java.util.LinkedList;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class RepeatingOptionalProgramParameters extends AbstractParser {

	public RepeatingOptionalProgramParameters(LinkedList<Token> tokenlist) {
		super(tokenlist);
	}
	
	public void parse() throws GrammarError {
		if (terminal == Terminals.RPAREN) {
			//TODO: stimmt es, dass dies einfach leer ist?
		} 
		else if(terminal == Terminals.COMMA){
			consume(Terminals.COMMA);
			new OptionalFlowModeParser().parse();
			new OptionalChangemodeParser().parse();
			new TypeIdentParser().parse();
			new RepeatingOptionalProgramParameters().parse();
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
	}
	
}
