package ch.fhnw.cpib.parser;

import java.util.LinkedList;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class FunctionDeclarationParser extends AbstractParser {

	public FunctionDeclarationParser() {
		super();
	}

	@Override
	public void parse() throws GrammarError {
		if (terminal == Terminals.FUN) {
			consume(Terminals.FUN);
			consume(Terminals.IDENT);
			new ParameterListParser().parse();
			consume(Terminals.RETURNS);
			new StorageDeclarationParser().parse();
			new OptionalGlobalImportsParser().parse();
			new OptionalLocalStorageDeclarationsParser().parse();
			consume(Terminals.DO);
			new BlockCmdParser().parse();
			consume(Terminals.ENDFUN);
		} 
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
		
	}

}
