package ch.fhnw.cpib.compiler.parser;

import ch.fhnw.cpib.compiler.cst.classes.ParameterList;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class ParameterListParser extends AbstractParser {

	public ParameterListParser() {
		super();
	}
	
	public IConcSyn.IParameterList parse() throws GrammarError {
		if(terminal == Terminals.LPAREN){
			Token lpar = (consume(Terminals.LPAREN));
			IConcSyn.IOptionalParameters optPar = new OptionalParametersParser().parse();
			Token rpar = (consume(Terminals.RPAREN));
			return new ParameterList(lpar, optPar, rpar);
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
	}
	
}
