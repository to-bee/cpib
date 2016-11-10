package ch.fhnw.cpib.parser;

import java.util.LinkedList;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class ExpressionParser extends AbstractParser {

	public ExpressionParser(LinkedList<Token> tokenlist) {
		super(tokenlist);
	}

	@Override
	public void parse() throws GrammarError {
		if (terminal == Terminals.RPAREN) {
			//TODO: leer?
		}
		else if (terminal == Terminals.LPAREN) {
			new Term1Parser().parse();
			new RepBooloprTerm1Parser().parse();
		} 
		else if (terminal == Terminals.ADDOPR) {
			new Term1Parser().parse();
			new RepBooloprTerm1Parser().parse();
		}
		else if (terminal == Terminals.NOTOPER) {
			new Term1Parser().parse();
			new RepBooloprTerm1Parser().parse();
		} 
		else if (terminal == Terminals.IDENT) {
			new Term1Parser().parse();
			new RepBooloprTerm1Parser().parse();
		} 
		else if (terminal == Terminals.LITERAL) {
			new Term1Parser().parse();
			new RepBooloprTerm1Parser().parse();
		} 
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
		
	}

}
