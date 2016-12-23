package ch.fhnw.cpib.parser;

import ch.fhnw.cpib.compiler.cst.classes.RepeatingOptionalExpressions;
import ch.fhnw.cpib.compiler.cst.classes.RepeatingOptionalExpressionsEps;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class RepeatingOptionalExpressionsParser extends AbstractParser {

	public RepeatingOptionalExpressionsParser() {
		super();
	}

	public IConcSyn.IRepeatingOptionalExpressions parse() throws GrammarError {
		if (terminal == Terminals.RPAREN) {
			return new RepeatingOptionalExpressionsEps();
		}
		else if (terminal == Terminals.COMMA) {
			Token comma = consume(Terminals.COMMA);
			IConcSyn.IExpression expr = new ExpressionParser().parse();
			IConcSyn.IRepeatingOptionalExpressions repOptExpr = new RepeatingOptionalExpressionsParser().parse();
			return new RepeatingOptionalExpressions(comma, expr, repOptExpr);
		} 
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
	}

}
