package ch.fhnw.cpib.parser;

import ch.fhnw.cpib.compiler.cst.classes.RepeatingOptionalProgramParameters;
import ch.fhnw.cpib.compiler.cst.classes.RepeatingOptionalProgramParametesEps;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class RepeatingOptionalProgramParametersParser extends AbstractParser {

	public RepeatingOptionalProgramParametersParser() {
		super();
	}
	
	public IConcSyn.IRepeatingOptionalProgramParameters parse() throws GrammarError {
		if (terminal == Terminals.RPAREN) {
			return new RepeatingOptionalProgramParametesEps();
		} 
		else if(terminal == Terminals.COMMA){
			Token comma = consume(Terminals.COMMA);
			IConcSyn.IOptionalFLOWMODE flowMode = new OptionalFlowModeParser().parse();
			IConcSyn.IOptionalCHANGEMODE changeMode = new OptionalChangemodeParser().parse();
			IConcSyn.ITypedIdent typedIdent = new TypedIdentParser().parse();
			IConcSyn.IRepeatingOptionalProgramParameters repOptProPar = new RepeatingOptionalProgramParametersParser().parse();
			return new RepeatingOptionalProgramParameters(comma, flowMode, changeMode, typedIdent, repOptProPar);
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
	}
	
}
