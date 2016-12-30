package ch.fhnw.cpib.compiler.parser;

import ch.fhnw.cpib.compiler.cst.classes.Term2;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class Term2Parser extends AbstractParser {

	public Term2Parser() {
		super();
	}
	
	public IConcSyn.ITerm2 parse() throws GrammarError {
		if (terminal == Terminals.LPAREN) {
			IConcSyn.ITerm3 t3 = new Term3Parser().parse();
			IConcSyn.IRepADDOPRterm3 repAddOpr = new RepAddoprTerm3Parser().parse();
			return new Term2(t3, repAddOpr);
		} 
		else if(terminal == Terminals.ADDOPR){
			IConcSyn.ITerm3 t3 = new Term3Parser().parse();
			IConcSyn.IRepADDOPRterm3 repAddOpr = new RepAddoprTerm3Parser().parse();
			return new Term2(t3, repAddOpr);
		}
		else if(terminal == Terminals.NOTOPER){
			IConcSyn.ITerm3 t3 = new Term3Parser().parse();
			IConcSyn.IRepADDOPRterm3 repAddOpr = new RepAddoprTerm3Parser().parse();
			return new Term2(t3, repAddOpr);
		}
		else if(terminal == Terminals.IDENT){
			IConcSyn.ITerm3 t3 = new Term3Parser().parse();
			IConcSyn.IRepADDOPRterm3 repAddOpr = new RepAddoprTerm3Parser().parse();
			return new Term2(t3, repAddOpr);
		}
		else if(terminal == Terminals.LITERAL){
			IConcSyn.ITerm3 t3 = new Term3Parser().parse();
			IConcSyn.IRepADDOPRterm3 repAddOpr = new RepAddoprTerm3Parser().parse();
			return new Term2(t3, repAddOpr);
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
	}
	
}
