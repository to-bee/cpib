package ch.fhnw.cpib.parser;

import ch.fhnw.cpib.compiler.classes.RepeatingOptionalParameters;
import ch.fhnw.cpib.compiler.classes.RepeatingOptionalParametersEps;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class RepeatingOptionalParametersParser extends AbstractParser {

	public RepeatingOptionalParametersParser() {
		super();
	}
	
	public IConcSyn.IRepeatingOptionalParameters parse() throws GrammarError {
		if(terminal == Terminals.RPAREN){
			return new RepeatingOptionalParametersEps();
		}
		else if(terminal == Terminals.COMMA){
			Token comma = consume(Terminals.COMMA);
			IConcSyn.IParameter par = new ParameterParser().parse();
			IConcSyn.IRepeatingOptionalParameters repOptPar = new RepeatingOptionalParametersParser().parse();
			return new RepeatingOptionalParameters(comma, par, repOptPar);
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
	}
	
}
