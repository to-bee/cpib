package ch.fhnw.cpib.parser;

import java.util.LinkedList;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class RepeatingOptionalParametersParser extends AbstractParser {

	public RepeatingOptionalParametersParser(LinkedList<Token> tokenlist) {
		super(tokenlist);
	}
	
	public void parse() throws GrammarError {
		if(terminal == Terminals.RPAREN){
			// TODO: leer?
		}
		else if(terminal == Terminals.COMMA){
			consume(Terminals.COMMA);
			new ParameterParser().parse();
			new RepeatingOptionalParametersParser();
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
	}
	
}
