package ch.fhnw.cpib.parser;

import ch.fhnw.cpib.compiler.cst.classes.Expressionlist;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class ExpressionListParser extends AbstractParser {

	public ExpressionListParser() {
		super();
	}

	public IConcSyn.IExpressionList parse() throws GrammarError {
		if (terminal == Terminals.LPAREN) {
			Token lpar = consume(Terminals.LPAREN);
			IConcSyn.IOptionalExpressions optExpr = new OptionalExpressionsParser().parse();
			Token rpar = consume(Terminals.RPAREN);
			return new Expressionlist(lpar, optExpr, rpar);
		} 
		else {
			System.out.println(tokenlist.toString());
			throw new GrammarError("GrammarError at: "+ this.getClass().toString() + " terminal found: " + terminal, 0);
		}
	}

}
