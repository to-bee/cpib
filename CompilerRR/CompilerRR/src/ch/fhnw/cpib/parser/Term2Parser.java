package ch.fhnw.cpib.parser;

import java.util.LinkedList;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class Term2Parser extends AbstractParser {

	public Term2Parser(LinkedList<Token> tokenlist) {
		super(tokenlist);
	}
	
	public void parse() throws GrammarError {
		if (terminal == Terminals.LPAREN) {
			new Term3Parser().parse();
			new RepAddoprTerm3().parse();
		} 
		else if(terminal == Terminals.ADDOPR){
			new Term3Parser().parse();
			new RepAddoprTerm3().parse();
		}
		else if(terminal == Terminals.NOTOPER){
			new Term3Parser().parse();
			new RepAddoprTerm3().parse();
		}
		else if(terminal == Terminals.IDENT){
			new Term3Parser().parse();
			new RepAddoprTerm3().parse();
		}
		else if(terminal == Terminals.LITERAL){
			new Term3Parser().parse();
			new RepAddoprTerm3().parse();
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
	}
	
}
