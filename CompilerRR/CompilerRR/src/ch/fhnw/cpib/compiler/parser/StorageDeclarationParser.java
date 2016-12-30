package ch.fhnw.cpib.compiler.parser;

import ch.fhnw.cpib.compiler.cst.classes.StorageDeclaration;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class StorageDeclarationParser extends AbstractParser {

	public StorageDeclarationParser() {
		super();
	}

	public IConcSyn.IStorageDeclaration parse() throws GrammarError {
		if (terminal == Terminals.IDENT) {
			IConcSyn.IOptionalCHANGEMODE changeMode = new OptionalChangemodeParser().parse();
			IConcSyn.ITypedIdent typedIdent = new TypedIdentParser().parse();
			return new StorageDeclaration(changeMode, typedIdent);
		} 
		else if (terminal == Terminals.CHANGEMODE) {
			IConcSyn.IOptionalCHANGEMODE changeMode = new OptionalChangemodeParser().parse();
			IConcSyn.ITypedIdent typedIdent = new TypedIdentParser().parse();
			return new StorageDeclaration(changeMode, typedIdent);
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
	}

}
