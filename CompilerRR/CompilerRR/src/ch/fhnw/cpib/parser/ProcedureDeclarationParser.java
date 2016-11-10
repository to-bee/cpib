package ch.fhnw.cpib.parser;

import java.util.LinkedList;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class ProcedureDeclarationParser extends AbstractParser {

	public ProcedureDeclarationParser(LinkedList<Token> tokenlist) {
		super(tokenlist);
	}

	@Override
	public void parse() throws GrammarError {
		if (terminal == Terminals.PROC) {
			consume(Terminals.PROC);
			consume(Terminals.IDENT);
			new ParameterListParser().parse();
			new OptionalGlobalImportsParser().parse();
			new OptionalLocalStorageDeclarationsParser().parse();
			consume(Terminals.DO);
			new BlockCmdParser().parse();
			consume(Terminals.ENDPROC);
		} 
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
		
	}

}
