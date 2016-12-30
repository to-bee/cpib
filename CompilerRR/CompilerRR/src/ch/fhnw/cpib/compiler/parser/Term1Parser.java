package ch.fhnw.cpib.compiler.parser;

import ch.fhnw.cpib.compiler.cst.classes.Term1;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.ITerm1;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class Term1Parser extends AbstractParser {

	public Term1Parser() {
		super();
	}
	
	public ITerm1 parse() throws GrammarError {
		if (terminal == Terminals.LPAREN) {
			IConcSyn.ITerm2 t2 = new Term2Parser().parse();
			IConcSyn.IRepRELOPRterm2 repRelOprT2 = new RepReloprTerm2Parser().parse();
			return new Term1(t2, repRelOprT2);
		} 
		else if(terminal == Terminals.ADDOPR){
			IConcSyn.ITerm2 t2 = new Term2Parser().parse();
			IConcSyn.IRepRELOPRterm2 repRelOprT2 = new RepReloprTerm2Parser().parse();
			return new Term1(t2, repRelOprT2);
		}
		else if(terminal == Terminals.NOTOPER){
			IConcSyn.ITerm2 t2 = new Term2Parser().parse();
			IConcSyn.IRepRELOPRterm2 repRelOprT2 = new RepReloprTerm2Parser().parse();
			return new Term1(t2, repRelOprT2);
		}
		else if(terminal == Terminals.IDENT){
			IConcSyn.ITerm2 t2 = new Term2Parser().parse();
			IConcSyn.IRepRELOPRterm2 repRelOprT2 = new RepReloprTerm2Parser().parse();
			return new Term1(t2, repRelOprT2);
		}
		else if(terminal == Terminals.LITERAL){
			IConcSyn.ITerm2 t2 = new Term2Parser().parse();
			IConcSyn.IRepRELOPRterm2 repRelOprT2 = new RepReloprTerm2Parser().parse();
			return new Term1(t2, repRelOprT2);
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
	}
	
}
