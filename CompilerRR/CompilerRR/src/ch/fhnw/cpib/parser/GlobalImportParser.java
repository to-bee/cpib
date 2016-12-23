package ch.fhnw.cpib.parser;

import ch.fhnw.cpib.compiler.cst.classes.GlobalImport;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class GlobalImportParser extends AbstractParser {

	public GlobalImportParser() {
		super();
	}

	public IConcSyn.IGlobalImport parse() throws GrammarError {
		if (terminal == Terminals.IDENT) {
			IConcSyn.IOptionalFLOWMODE flowMode = new OptionalFlowModeParser().parse();
			IConcSyn.IOptionalCHANGEMODE changeMode = new OptionalChangemodeParser().parse();
			Token ident = consume(Terminals.IDENT);
			return new GlobalImport(flowMode, changeMode, ident);
		} 
		else if (terminal == Terminals.CHANGEMODE) {
			IConcSyn.IOptionalFLOWMODE flowMode = new OptionalFlowModeParser().parse();
			IConcSyn.IOptionalCHANGEMODE changeMode = new OptionalChangemodeParser().parse();
			Token ident = consume(Terminals.IDENT);
			return new GlobalImport(flowMode, changeMode, ident);
		} 
		else if (terminal == Terminals.FLOWMODE) {
			IConcSyn.IOptionalFLOWMODE flowMode = new OptionalFlowModeParser().parse();
			IConcSyn.IOptionalCHANGEMODE changeMode = new OptionalChangemodeParser().parse();
			Token ident = consume(Terminals.IDENT);
			return new GlobalImport(flowMode, changeMode, ident);
		} 
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString() + " terminal found: " + terminal, 0);
		}
	}

}
