package ch.fhnw.cpib.parser;

import ch.fhnw.cpib.compiler.cst.classes.OptionalGlobalDeclarations;
import ch.fhnw.cpib.compiler.cst.classes.OptionalGlobalDeclarationsEps;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class OptionalGlobalDeclarationsParser extends AbstractParser {

	public OptionalGlobalDeclarationsParser() {
		super();
	}

	public IConcSyn.IOptionalGlobalDeclarations parse() throws GrammarError {
		if (terminal == Terminals.DO) {
			return new OptionalGlobalDeclarationsEps();
		}
		else if (terminal == Terminals.GLOBAL) {
			Token globalToken = consume(Terminals.GLOBAL);
			IConcSyn.IDeclarations decl = new DeclarationsParser().parse();
			return new OptionalGlobalDeclarations(globalToken, decl);
		} 
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString() + " terminal found: " + terminal, 0);
		}
	}

}
