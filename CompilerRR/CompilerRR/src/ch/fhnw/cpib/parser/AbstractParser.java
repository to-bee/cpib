package ch.fhnw.cpib.parser;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public abstract class AbstractParser implements IParser{
	
	private LinkedList<Token> tokenlist;
	private Set<String> nonTerminals = new HashSet<String>();
	protected Token token;
	protected Terminals terminal;
	
	public AbstractParser(LinkedList<Token> tokenlist) {
		this.tokenlist = tokenlist;
		
		token = this.tokenlist.removeFirst();
		terminal = token.getTerminal();
		
	}
	
	public Token consume(Terminals expectedTerminal) throws GrammarError {
		
		if (terminal == expectedTerminal) {
			Token consumedToken = token;
			
			if (terminal != Terminals.SENTINEL) {
				token = tokenlist.removeFirst();
				
				terminal = token.getTerminal();
			}
			return consumedToken;
		}
		else {
			throw new GrammarError("terminal expected: " + expectedTerminal + ", terminal found: " + terminal, 0);
		}
		
	}
	

}
