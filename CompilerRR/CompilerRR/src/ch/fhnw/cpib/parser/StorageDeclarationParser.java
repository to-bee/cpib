package ch.fhnw.cpib.parser;

import java.util.LinkedList;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class StorageDeclarationParser extends AbstractParser {

	public StorageDeclarationParser() {
		super();
	}

	@Override
	public void parse() throws GrammarError {
		if (terminal == Terminals.IDENT) {
			new OptionalChangemodeParser().parse();
			new TypeIdentParser().parse();
		} 
		else if (terminal == Terminals.CHANGEMODE) {
			new OptionalChangemodeParser().parse();
			new TypeIdentParser().parse();
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
		
	}

}
