package ch.fhnw.cpib.compiler.parser;

import ch.fhnw.cpib.compiler.cst.classes.OptionalGlobalImports;
import ch.fhnw.cpib.compiler.cst.classes.OptionalGlobalImportsEps;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class OptionalGlobalImportsParser extends AbstractParser {

	public OptionalGlobalImportsParser() {
		super();
	}

	public IConcSyn.IOptionalGlobalImports parse() throws GrammarError {
		if (terminal == Terminals.DO) {
			return new OptionalGlobalImportsEps();
		} 
		else if (terminal == Terminals.LOCAL) {
			return new OptionalGlobalImportsEps();
		}
		else if (terminal == Terminals.GLOBAL) {
			Token global = consume(Terminals.GLOBAL);
			IConcSyn.IGlobalImport globImp = new GlobalImportParser().parse();
			IConcSyn.IRepeatingOptionalGlobalImports repOptGlobImp = new RepeatingOptionalGlobalImportsParser().parse();
			return new OptionalGlobalImports(global, globImp, repOptGlobImp);
		} 
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString() + " terminal found: " + terminal, 0);
		}
	}

}
