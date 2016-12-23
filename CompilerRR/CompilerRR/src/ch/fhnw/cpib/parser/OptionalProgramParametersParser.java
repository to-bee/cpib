package ch.fhnw.cpib.parser;

import ch.fhnw.cpib.compiler.cst.classes.OptionalProgramParameters;
import ch.fhnw.cpib.compiler.cst.classes.OptionalProgramParametersEps;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class OptionalProgramParametersParser extends AbstractParser {

	public OptionalProgramParametersParser() {
		super();
	}

	public IConcSyn.IOptionalProgramParameters parse() throws GrammarError {
		if (terminal == Terminals.RPAREN) {
			return new OptionalProgramParametersEps();
		}
		else if (terminal == Terminals.IDENT) {
			IConcSyn.IOptionalFLOWMODE flowMode = new OptionalFlowModeParser().parse();
			IConcSyn.IOptionalCHANGEMODE changeMode = new OptionalChangemodeParser().parse();
			IConcSyn.ITypedIdent typedIdent = new TypedIdentParser().parse();
			IConcSyn.IRepeatingOptionalProgramParameters repOptProPar = new RepeatingOptionalProgramParametersParser().parse();
			return new OptionalProgramParameters(flowMode, changeMode, typedIdent, repOptProPar);
		} 
		else if (terminal == Terminals.CHANGEMODE) {
			IConcSyn.IOptionalFLOWMODE flowMode = new OptionalFlowModeParser().parse();
			IConcSyn.IOptionalCHANGEMODE changeMode = new OptionalChangemodeParser().parse();
			IConcSyn.ITypedIdent typedIdent = new TypedIdentParser().parse();
			IConcSyn.IRepeatingOptionalProgramParameters repOptProPar = new RepeatingOptionalProgramParametersParser().parse();
			return new OptionalProgramParameters(flowMode, changeMode, typedIdent, repOptProPar);
		}
		else if (terminal == Terminals.FLOWMODE) {
			IConcSyn.IOptionalFLOWMODE flowMode = new OptionalFlowModeParser().parse();
			IConcSyn.IOptionalCHANGEMODE changeMode = new OptionalChangemodeParser().parse();
			IConcSyn.ITypedIdent typedIdent = new TypedIdentParser().parse();
			IConcSyn.IRepeatingOptionalProgramParameters repOptProPar = new RepeatingOptionalProgramParametersParser().parse();
			return new OptionalProgramParameters(flowMode, changeMode, typedIdent, repOptProPar);
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
	}

}
