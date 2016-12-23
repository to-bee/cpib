package ch.fhnw.cpib.parser;

import ch.fhnw.cpib.compiler.cst.classes.ProgramParameterList;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class ProgramParameterListParser extends AbstractParser {

	public ProgramParameterListParser() {
		super();
	}

	public IConcSyn.IProgamParameterList parse() throws GrammarError {
		if (terminal == Terminals.LPAREN) {
			Token lpar =consume(Terminals.LPAREN);
			IConcSyn.IOptionalProgramParameters optProPar = new OptionalProgramParametersParser().parse();
			Token rpar = consume(Terminals.RPAREN);
			return new ProgramParameterList(lpar, optProPar, rpar);
		} 
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
	}

}
