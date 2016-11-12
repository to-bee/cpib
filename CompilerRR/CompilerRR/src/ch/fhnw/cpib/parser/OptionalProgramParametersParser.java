package ch.fhnw.cpib.parser;

import java.util.LinkedList;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class OptionalProgramParametersParser extends AbstractParser {

	public OptionalProgramParametersParser() {
		super();
	}

	@Override
	public void parse() throws GrammarError {
		if (terminal == Terminals.RPAREN) {
			// TODO: leer?
		}
		else if (terminal == Terminals.IDENT) {
			new OptionalFlowModeParser().parse();
			new OptionalChangemodeParser().parse();
			new TypeIdentParser().parse();
			new RepeatingOptionalProgramParametersParser().parse();
		} 
		else if (terminal == Terminals.CHANGEMODE) {
			new OptionalFlowModeParser().parse();
			new OptionalChangemodeParser().parse();
			new TypeIdentParser().parse();
			new RepeatingOptionalProgramParametersParser().parse();
		}
		else if (terminal == Terminals.FLOWMODE) {
			new OptionalFlowModeParser().parse();
			new OptionalChangemodeParser().parse();
			new TypeIdentParser().parse();
			new RepeatingOptionalProgramParametersParser().parse();
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
		
	}

}
