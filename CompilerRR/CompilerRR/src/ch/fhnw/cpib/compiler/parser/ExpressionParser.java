package ch.fhnw.cpib.compiler.parser;

import ch.fhnw.cpib.compiler.cst.classes.Expression;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class ExpressionParser extends AbstractParser {

	public ExpressionParser() {
		super();
	}

	public IConcSyn.IExpression parse() throws GrammarError {
		if (terminal == Terminals.LPAREN) {
			IConcSyn.ITerm1 t1 = new Term1Parser().parse();
			IConcSyn.IRepBOOLOPRterm1 repBool = new RepBooloprTerm1Parser().parse();
			return new Expression(t1, repBool);
		} 
		else if (terminal == Terminals.ADDOPR) {
			IConcSyn.ITerm1 t1 = new Term1Parser().parse();
			IConcSyn.IRepBOOLOPRterm1 repBool = new RepBooloprTerm1Parser().parse();
			return new Expression(t1, repBool);
		}
		else if (terminal == Terminals.NOTOPER) {
			IConcSyn.ITerm1 t1 = new Term1Parser().parse();
			IConcSyn.IRepBOOLOPRterm1 repBool = new RepBooloprTerm1Parser().parse();
			return new Expression(t1, repBool);
		} 
		else if (terminal == Terminals.IDENT) {
			IConcSyn.ITerm1 t1 = new Term1Parser().parse();
			IConcSyn.IRepBOOLOPRterm1 repBool = new RepBooloprTerm1Parser().parse();
			return new Expression(t1, repBool);
		} 
		else if (terminal == Terminals.LITERAL) {
			IConcSyn.ITerm1 t1 = new Term1Parser().parse();
			IConcSyn.IRepBOOLOPRterm1 repBool = new RepBooloprTerm1Parser().parse();
			return new Expression(t1, repBool);
		} 
		else {
			System.out.println(tokenlist.toString());
			throw new GrammarError("GrammarError at: "+ this.getClass().toString() + " terminal found: " + terminal, 0);
		}
	}

}
