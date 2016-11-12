package ch.fhnw.cpib.parser;

import java.util.LinkedList;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class DeclarationsParser extends AbstractParser {

	public DeclarationsParser() {
		super();
	}

	@Override
	public void parse() throws GrammarError {
		if (terminal == Terminals.PROC) {
			new DeclarationParser().parse();
			new RepeatingOptionalDeclarationsParser().parse();
		}
		else if (terminal == Terminals.FUN) {
			new DeclarationParser().parse();
			new RepeatingOptionalDeclarationsParser().parse();
		}
		else if (terminal == Terminals.IDENT) {
			new DeclarationParser().parse();
			new RepeatingOptionalDeclarationsParser().parse();
		}
		else if (terminal == Terminals.CHANGEMODE) {
			new DeclarationParser().parse();
			new RepeatingOptionalDeclarationsParser().parse();
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
		
	}

}
