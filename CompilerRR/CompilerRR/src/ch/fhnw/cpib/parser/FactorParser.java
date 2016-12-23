package ch.fhnw.cpib.parser;

import ch.fhnw.cpib.compiler.classes.FactorExpression;
import ch.fhnw.cpib.compiler.classes.FactorIdent;
import ch.fhnw.cpib.compiler.classes.FactorLiteral;
import ch.fhnw.cpib.compiler.classes.FactorMoniadic;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class FactorParser extends AbstractParser {

	public FactorParser() {
		super();
	}
	
	public IConcSyn.IFactor parse() throws GrammarError {
		if (terminal == Terminals.LITERAL) {
			return new FactorLiteral(consume(Terminals.LITERAL));
		} 
		else if(terminal == Terminals.IDENT){
			Token ident = consume(Terminals.IDENT);
			IConcSyn.IOptionalIdent optIdent = new OptionalIdentParser().parse();
			return new FactorIdent(ident, optIdent);
		}
		else if(terminal == Terminals.ADDOPR){
			IConcSyn.IMonadicOperator monOpr = new MonadicOperatorParser().parse();
			IConcSyn.IFactor fac = new FactorParser().parse();
			return new FactorMoniadic(monOpr, fac);
		}
		else if(terminal == Terminals.NOTOPER){
			IConcSyn.IMonadicOperator monOpr = new MonadicOperatorParser().parse();
			IConcSyn.IFactor fac = new FactorParser().parse();
			return new FactorMoniadic(monOpr, fac);
		}
		else if(terminal == Terminals.LPAREN){
			Token lpar = consume(Terminals.LPAREN);
			IConcSyn.IExpression expr = new ExpressionParser().parse();
			Token rpar = consume(Terminals.RPAREN);
			return new FactorExpression(lpar, expr, rpar);
		}
		else {
			System.out.println(tokenlist.toString());
			throw new GrammarError("GrammarError at: "+ this.getClass().toString() + " terminal found: " + terminal, 0);
		}
	}
	
}
