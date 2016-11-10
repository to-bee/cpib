package ch.fhnw.cpib.parser;

import java.util.LinkedList;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class GlobalImportParser extends AbstractParser {

	public GlobalImportParser(LinkedList<Token> tokenlist) {
		super(tokenlist);
	}

	@Override
	public void parse() throws GrammarError {
		if (terminal == Terminals.IDENT) {
			new OptionalFlowmodeParser().parse();
			new OptionalChangemodeParser().parse();
			consume(Terminals.IDENT);
		} 
		else if (terminal == Terminals.CHANGEMODE) {
			new OptionalFlowmodeParser().parse();
			new OptionalChangemodeParser().parse();
			consume(Terminals.IDENT);
		} 
		else if (terminal == Terminals.FLOWMODE) {
			new OptionalFlowmodeParser().parse();
			new OptionalChangemodeParser().parse();
			consume(Terminals.IDENT);
		} 
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
		
	}

}
