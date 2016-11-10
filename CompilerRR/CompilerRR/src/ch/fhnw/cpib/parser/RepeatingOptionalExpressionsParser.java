package ch.fhnw.cpib.parser;

import java.util.LinkedList;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class RepeatingOptionalExpressionsParser extends AbstractParser {

	public RepeatingOptionalExpressionsParser(LinkedList<Token> tokenlist) {
		super(tokenlist);
	}

	@Override
	public void parse() throws GrammarError {
		if (terminal == Terminals.RPAREN) {
			//TODO: leer?
		}
		else if (terminal == Terminals.COMMA) {
			consume(Terminals.COMMA),
			new ExpressionParser().parse();
			new RepeatingOptionalExpressionsParser().parse();
		} 
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
		
	}

}
