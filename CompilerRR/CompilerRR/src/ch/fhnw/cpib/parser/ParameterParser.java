package ch.fhnw.cpib.parser;

import ch.fhnw.cpib.compiler.cst.classes.Parameter;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class ParameterParser extends AbstractParser {

	public ParameterParser() {
		super();
	}

	public IConcSyn.IParameter parse() throws GrammarError {
		if (terminal == Terminals.IDENT) {
			IConcSyn.IOptionalFLOWMODE flowMode = new OptionalFlowModeParser().parse();
			IConcSyn.IOptionalMECHMODE mechMode = new OptionalMechmodeParser().parse();
			IConcSyn.IStorageDeclaration stoDecl = new StorageDeclarationParser().parse();
			return new Parameter(flowMode, mechMode, stoDecl);
		} 
		else if (terminal == Terminals.CHANGEMODE) {
			IConcSyn.IOptionalFLOWMODE flowMode = new OptionalFlowModeParser().parse();
			IConcSyn.IOptionalMECHMODE mechMode = new OptionalMechmodeParser().parse();
			IConcSyn.IStorageDeclaration stoDecl = new StorageDeclarationParser().parse();
			return new Parameter(flowMode, mechMode, stoDecl);
		}
		else if (terminal == Terminals.MECHMODE) {
			IConcSyn.IOptionalFLOWMODE flowMode = new OptionalFlowModeParser().parse();
			IConcSyn.IOptionalMECHMODE mechMode = new OptionalMechmodeParser().parse();
			IConcSyn.IStorageDeclaration stoDecl = new StorageDeclarationParser().parse();
			return new Parameter(flowMode, mechMode, stoDecl);
		}
		else if (terminal == Terminals.FLOWMODE) {
			IConcSyn.IOptionalFLOWMODE flowMode = new OptionalFlowModeParser().parse();
			IConcSyn.IOptionalMECHMODE mechMode = new OptionalMechmodeParser().parse();
			IConcSyn.IStorageDeclaration stoDecl = new StorageDeclarationParser().parse();
			return new Parameter(flowMode, mechMode, stoDecl);
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
	}

}
