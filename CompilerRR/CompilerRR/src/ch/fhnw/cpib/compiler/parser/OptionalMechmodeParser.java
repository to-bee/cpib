package ch.fhnw.cpib.compiler.parser;

import ch.fhnw.cpib.compiler.cst.classes.OptionalMechmode;
import ch.fhnw.cpib.compiler.cst.classes.OptionalMechmodeEps;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class OptionalMechmodeParser extends AbstractParser {

	public OptionalMechmodeParser() {
		super();
	}

	public IConcSyn.IOptionalMECHMODE parse() throws GrammarError {
		if (terminal == Terminals.MECHMODE) {
			return new OptionalMechmode(consume(Terminals.MECHMODE));
		}
		else if (terminal == Terminals.IDENT) {
			return new OptionalMechmodeEps();
		} 
		else if (terminal == Terminals.CHANGEMODE) {
			return new OptionalMechmodeEps();
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
	}

}
