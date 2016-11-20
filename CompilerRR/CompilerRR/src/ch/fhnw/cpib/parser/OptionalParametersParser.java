package ch.fhnw.cpib.parser;

import java.util.LinkedList;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class OptionalParametersParser extends AbstractParser {

	public OptionalParametersParser() {
		super();
	}

	@Override
	public void parse() throws GrammarError {
		if (terminal == Terminals.RPAREN) {
			// TODO: leer?
		}
		else if (terminal == Terminals.IDENT) {
			new ParameterParser().parse();
			new RepeatingOptionalParametersParser().parse();
		} 
		else if (terminal == Terminals.CHANGEMODE) {
			new ParameterParser().parse();
			new RepeatingOptionalParametersParser().parse();
		}
		else if (terminal == Terminals.MECHMODE) {
			new ParameterParser().parse();
			new RepeatingOptionalParametersParser().parse();
		}
		else if (terminal == Terminals.FLOWMODE) {
			new ParameterParser().parse();
			new RepeatingOptionalParametersParser().parse();
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
		
	}

}
