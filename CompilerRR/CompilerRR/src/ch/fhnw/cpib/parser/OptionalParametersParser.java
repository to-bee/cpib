package ch.fhnw.cpib.parser;

import ch.fhnw.cpib.compiler.classes.OptionalParameters;
import ch.fhnw.cpib.compiler.classes.OptionalParametersEps;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class OptionalParametersParser extends AbstractParser {

	public OptionalParametersParser() {
		super();
	}

	//<parameter> <repeatingOptionalParameters>
	public IConcSyn.IOptionalParameters parse() throws GrammarError {
		if (terminal == Terminals.RPAREN) {
			return new OptionalParametersEps();
		}
		else if (terminal == Terminals.IDENT) {
			IConcSyn.IParameter par = new ParameterParser().parse();
			IConcSyn.IRepeatingOptionalParameters repOptPar = new RepeatingOptionalParametersParser().parse();
			return new OptionalParameters(par, repOptPar);
		} 
		else if (terminal == Terminals.CHANGEMODE) {
			IConcSyn.IParameter par = new ParameterParser().parse();
			IConcSyn.IRepeatingOptionalParameters repOptPar = new RepeatingOptionalParametersParser().parse();
			return new OptionalParameters(par, repOptPar);
		}
		else if (terminal == Terminals.MECHMODE) {
			IConcSyn.IParameter par = new ParameterParser().parse();
			IConcSyn.IRepeatingOptionalParameters repOptPar = new RepeatingOptionalParametersParser().parse();
			return new OptionalParameters(par, repOptPar);
		}
		else if (terminal == Terminals.FLOWMODE) {
			IConcSyn.IParameter par = new ParameterParser().parse();
			IConcSyn.IRepeatingOptionalParameters repOptPar = new RepeatingOptionalParametersParser().parse();
			return new OptionalParameters(par, repOptPar);
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
	}

}
