package ch.fhnw.cpib.parser;

import ch.fhnw.cpib.compiler.classes.OptionalExpressions;
import ch.fhnw.cpib.compiler.classes.OptionalExpressionsEps;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class OptionalExpressionsParser extends AbstractParser {

	public OptionalExpressionsParser() {
		super();
	}

	public IConcSyn.IOptionalExpressions parse() throws GrammarError {
		if (terminal == Terminals.RPAREN) {
			return new OptionalExpressionsEps();
		}
		else if (terminal == Terminals.LPAREN) {
			IConcSyn.IExpression expr = new ExpressionParser().parse();
			IConcSyn.IRepeatingOptionalExpressions repOptExpr = new RepeatingOptionalExpressionsParser().parse();
			return new OptionalExpressions(expr, repOptExpr);
		} 
		else if (terminal == Terminals.ADDOPR) {
			IConcSyn.IExpression expr = new ExpressionParser().parse();
			IConcSyn.IRepeatingOptionalExpressions repOptExpr = new RepeatingOptionalExpressionsParser().parse();
			return new OptionalExpressions(expr, repOptExpr);
		}
		else if (terminal == Terminals.NOTOPER) {
			IConcSyn.IExpression expr = new ExpressionParser().parse();
			IConcSyn.IRepeatingOptionalExpressions repOptExpr = new RepeatingOptionalExpressionsParser().parse();
			return new OptionalExpressions(expr, repOptExpr);
		} 
		else if (terminal == Terminals.IDENT) {
			IConcSyn.IExpression expr = new ExpressionParser().parse();
			IConcSyn.IRepeatingOptionalExpressions repOptExpr = new RepeatingOptionalExpressionsParser().parse();
			return new OptionalExpressions(expr, repOptExpr);
		} 
		else if (terminal == Terminals.LITERAL) {
			IConcSyn.IExpression expr = new ExpressionParser().parse();
			IConcSyn.IRepeatingOptionalExpressions repOptExpr = new RepeatingOptionalExpressionsParser().parse();
			return new OptionalExpressions(expr, repOptExpr);
		} 
		else {
			System.out.println(tokenlist.toString());
			throw new GrammarError("GrammarError at: "+ this.getClass().toString() + " terminal found: " + terminal, 0);
		}
	}

}
