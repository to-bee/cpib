package ch.fhnw.cpib.parser;

import java.util.LinkedList;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class FactorParser extends AbstractParser {

	public FactorParser() {
		super();
	}
	
	public void parse() throws GrammarError {
		if (terminal == Terminals.LITERAL) {
			consume(Terminals.LITERAL);
		} 
		else if(terminal == Terminals.IDENT){
			consume(Terminals.IDENT);
			new OptionalIdentParser().parse();
		}
		else if(terminal == Terminals.ADDOPR){
			new MonadicOperatorParser().parse();
			new FactorParser().parse();
		}
		else if(terminal == Terminals.NOTOPER){
			new MonadicOperatorParser().parse();
			new FactorParser().parse();
		}
		else if(terminal == Terminals.LPAREN){
			consume(Terminals.LPAREN);
			new ExpressionParser().parse();
			consume(Terminals.RPAREN);
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
	}
	
}
