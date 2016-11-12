package ch.fhnw.cpib.parser;

import java.util.LinkedList;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class RepeatingOptionalGlobalImportsParser extends AbstractParser {

	public RepeatingOptionalGlobalImportsParser() {
		super();
	}

	@Override
	public void parse() throws GrammarError {
		if (terminal == Terminals.DO) {
			// TODO: leer?
		} 
		else if (terminal == Terminals.LOCAL) {
			// TODO: leer?
		} 
		else if (terminal == Terminals.COMMA) {
			consume(Terminals.COMMA);
			new GlobalImportParser().parse();
			new RepeatingOptionalGlobalImportsParser().parse();
		} 
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
		
	}

}
