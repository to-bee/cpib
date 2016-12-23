package ch.fhnw.cpib.parser;

import ch.fhnw.cpib.compiler.cst.classes.MoniadicOperatorAddOpr;
import ch.fhnw.cpib.compiler.cst.classes.MoniadicOperatorNot;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class MonadicOperatorParser extends AbstractParser {

	public MonadicOperatorParser() {
		super();
	}
	
	public IConcSyn.IMonadicOperator parse() throws GrammarError {
		if (terminal == Terminals.NOTOPER) {
			return new MoniadicOperatorNot(consume(Terminals.NOTOPER));
		} 
		else if(terminal == Terminals.ADDOPR){
			return new MoniadicOperatorAddOpr(consume(Terminals.ADDOPR));
		}
		else {
			System.out.println(tokenlist.toString());
			throw new GrammarError("GrammarError at: "+ this.getClass().toString() + " terminal found: " + terminal, 0);
		}
	}
	
}
