package ch.fhnw.cpib.parser;

import java.util.LinkedList;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class Term1Parser extends AbstractParser {

	public Term1Parser(LinkedList<Token> tokenlist) {
		super(tokenlist);
	}
	
	public void parse() throws GrammarError {
		if (terminal == Terminals.LPAREN) {
			new Term2Parser().parse();
			new RepReloprTerm2Parser().parse();
		} 
		else if(terminal == Terminals.ADDOPR){
			new Term2Parser().parse();
			new RepReloprTerm2Parser().parse();
		}
		else if(terminal == Terminals.NOTOPER){
			new Term2Parser().parse();
			new RepReloprTerm2Parser().parse();
		}
		else if(terminal == Terminals.IDENT){
			new Term2Parser().parse();
			new RepReloprTerm2Parser().parse();
		}
		else if(terminal == Terminals.LITERAL){
			new Term2Parser().parse();
			new RepReloprTerm2Parser().parse();
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
	}
	
}
