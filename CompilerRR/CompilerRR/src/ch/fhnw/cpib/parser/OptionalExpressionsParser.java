package ch.fhnw.cpib.parser;

import java.util.LinkedList;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class OptionalExpressionsParser extends AbstractParser {

	public OptionalExpressionsParser() {
		super();
	}

	@Override
	public void parse() throws GrammarError {
		if (terminal == Terminals.RPAREN) {
			//TODO: leer?
		}
		else if (terminal == Terminals.LPAREN) {
			new ExpressionParser().parse();
			new RepeatingOptionalExpressionsParser().parse();
		} 
		else if (terminal == Terminals.ADDOPR) {
			new ExpressionParser().parse();
			new RepeatingOptionalExpressionsParser().parse();
		}
		else if (terminal == Terminals.NOTOPER) {
			new ExpressionParser().parse();
			new RepeatingOptionalExpressionsParser().parse();
		} 
		else if (terminal == Terminals.IDENT) {
			new ExpressionParser().parse();
			new RepeatingOptionalExpressionsParser().parse();
		} 
		else if (terminal == Terminals.LITERAL) {
			new ExpressionParser().parse();
			new RepeatingOptionalExpressionsParser().parse();
		} 
		else {
			System.out.println(tokenlist.toString());
			throw new GrammarError("GrammarError at: "+ this.getClass().toString() + " terminal found: " + terminal, 0);
		}
		
	}

}
