package ch.fhnw.cpib.parser;

import ch.fhnw.cpib.compiler.classes.RepeatingOptionalGlobalImportEps;
import ch.fhnw.cpib.compiler.classes.RepeatingOptionalGlobalImports;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class RepeatingOptionalGlobalImportsParser extends AbstractParser {

	public RepeatingOptionalGlobalImportsParser() {
		super();
	}

	public IConcSyn.IRepeatingOptionalGlobalImports parse() throws GrammarError {
		if (terminal == Terminals.DO) {
			return new RepeatingOptionalGlobalImportEps();
		} 
		else if (terminal == Terminals.LOCAL) {
			return new RepeatingOptionalGlobalImportEps();
		} 
		else if (terminal == Terminals.COMMA) {
			Token commaToken = consume(Terminals.COMMA);
			IConcSyn.IGlobalImport globImp = new GlobalImportParser().parse();
			IConcSyn.IRepeatingOptionalGlobalImports repOptGlobImp = new RepeatingOptionalGlobalImportsParser().parse();
			return new RepeatingOptionalGlobalImports(commaToken, globImp, repOptGlobImp);
		} 
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
	}

}
