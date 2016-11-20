package ch.fhnw.cpib.parser;

import java.util.LinkedList;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class ProgramParameterListParser extends AbstractParser {

	public ProgramParameterListParser() {
		super();
	}

	@Override
	public void parse() throws GrammarError {
		if (terminal == Terminals.LPAREN) {
			consume(Terminals.LPAREN);
			new OptionalProgramParametersParser().parse();
			consume(Terminals.RPAREN);
		} 
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
		
	}

}
