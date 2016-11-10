package ch.fhnw.cpib.parser;

import java.util.LinkedList;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class Term3Parser extends AbstractParser {

	public Term3Parser(LinkedList<Token> tokenlist) {
		super(tokenlist);
	}
	
	public void parse() throws GrammarError {
		if (terminal == Terminals.LPAREN) {
			new FactorParser().parse();
			new RepMultoprFactor().parse();
		} 
		else if(terminal == Terminals.ADDOPR){
			new FactorParser().parse();
			new RepMultoprFactor().parse();
		}
		else if(terminal == Terminals.NOTOPER){
			new FactorParser().parse();
			new RepMultoprFactor().parse();
		}
		else if(terminal == Terminals.IDENT){
			new FactorParser().parse();
			new RepMultoprFactor().parse();
		}
		else if(terminal == Terminals.LITERAL){
			new FactorParser().parse();
			new RepMultoprFactor().parse();
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
	}
	
}
