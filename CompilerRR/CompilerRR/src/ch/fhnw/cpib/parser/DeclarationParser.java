package ch.fhnw.cpib.parser;

import java.util.LinkedList;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class DeclarationParser extends AbstractParser {

	public DeclarationParser(LinkedList<Token> tokenlist) {
		super(tokenlist);
	}

	@Override
	public void parse() throws GrammarError {
		if (terminal == Terminals.IDENT) {
			new StorageDeclarationParser().parse();
		} 
		else if (terminal == Terminals.CHANGEMODE) {
			new StorageDeclarationParser().parse();
		}
		else if (terminal == Terminals.FUN) {
			new FunctionDeclarationParser().parse();
		} 
		else if (terminal == Terminals.PROC) {
			new ProcedureDeclarationParser().parse();
		} 
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
		
	}

}
