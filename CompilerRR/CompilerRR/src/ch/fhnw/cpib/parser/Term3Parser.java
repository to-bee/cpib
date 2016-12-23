package ch.fhnw.cpib.parser;

import ch.fhnw.cpib.compiler.cst.classes.Term3;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class Term3Parser extends AbstractParser {

	public Term3Parser() {
		super();
	}
	
	public IConcSyn.ITerm3 parse() throws GrammarError {
		if (terminal == Terminals.LPAREN) {
			IConcSyn.IFactor factor = new FactorParser().parse();
			IConcSyn.IRepMULTOPRfactor repMulOprFac = new RepMultoprFactorParser().parse();
			return new Term3(factor, repMulOprFac);
		} 
		else if(terminal == Terminals.ADDOPR){
			IConcSyn.IFactor factor = new FactorParser().parse();
			IConcSyn.IRepMULTOPRfactor repMulOprFac = new RepMultoprFactorParser().parse();
			return new Term3(factor, repMulOprFac);
		}
		else if(terminal == Terminals.NOTOPER){
			IConcSyn.IFactor factor = new FactorParser().parse();
			IConcSyn.IRepMULTOPRfactor repMulOprFac = new RepMultoprFactorParser().parse();
			return new Term3(factor, repMulOprFac);
		}
		else if(terminal == Terminals.IDENT){
			IConcSyn.IFactor factor = new FactorParser().parse();
			IConcSyn.IRepMULTOPRfactor repMulOprFac = new RepMultoprFactorParser().parse();
			return new Term3(factor, repMulOprFac);
		}
		else if(terminal == Terminals.LITERAL){
			IConcSyn.IFactor factor = new FactorParser().parse();
			IConcSyn.IRepMULTOPRfactor repMulOprFac = new RepMultoprFactorParser().parse();
			return new Term3(factor, repMulOprFac);
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
	}
	
}
