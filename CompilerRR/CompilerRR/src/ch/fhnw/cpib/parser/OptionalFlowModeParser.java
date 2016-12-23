package ch.fhnw.cpib.parser;

import ch.fhnw.cpib.compiler.classes.OptionalFlowmode;
import ch.fhnw.cpib.compiler.classes.OptionalFlowmodeEps;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class OptionalFlowModeParser extends AbstractParser {

	public OptionalFlowModeParser() {
		super();
	}

	public IConcSyn.IOptionalFLOWMODE parse() throws GrammarError {
		if (terminal == Terminals.MECHMODE) {
			return new OptionalFlowmodeEps();
		}
		else if (terminal == Terminals.IDENT) {
			return new OptionalFlowmodeEps();
		} 
		else if (terminal == Terminals.CHANGEMODE) {
			return new OptionalFlowmodeEps();
		}
		else if (terminal == Terminals.FLOWMODE) {
			return new OptionalFlowmode(consume(Terminals.FLOWMODE));
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString() + " terminal found: " + terminal, 0);
		}
	}

}
