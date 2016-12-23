package ch.fhnw.cpib.parser;

import ch.fhnw.cpib.compiler.cst.classes.OptionalChangemode;
import ch.fhnw.cpib.compiler.cst.classes.OptionalChangemodeEps;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class OptionalChangemodeParser extends AbstractParser {

	public OptionalChangemodeParser() {
		super();
	}

	public IConcSyn.IOptionalCHANGEMODE parse() throws GrammarError {
		if (terminal == Terminals.IDENT) {
			return new OptionalChangemodeEps();
		} 
		else if (terminal == Terminals.CHANGEMODE) {
			return new OptionalChangemode(consume(Terminals.CHANGEMODE));
		}
		else {
			System.out.println(tokenlist.toString());
			System.out.println(token);
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
	}

}
