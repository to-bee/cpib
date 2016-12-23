package ch.fhnw.cpib.parser;

import ch.fhnw.cpib.compiler.cst.classes.OptionalLocalStorageDeclaration;
import ch.fhnw.cpib.compiler.cst.classes.OptionalLocalStorageDeclarationEps;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class OptionalLocalStorageDeclarationsParser extends AbstractParser {

	public OptionalLocalStorageDeclarationsParser() {
		super();
	}
	
	public IConcSyn.IOptionalLocalStorageDeclarations parse() throws GrammarError {
		if (terminal == Terminals.DO) {
			return new OptionalLocalStorageDeclarationEps();
		} 
		else if(terminal == Terminals.LOCAL){
			Token localToken = consume(Terminals.LOCAL);
			IConcSyn.IStorageDeclaration stoDecl = new StorageDeclarationParser().parse();
			IConcSyn.IRepeatingOptionalStorageDeclarations repOptStoDecl = new RepeatingOptionalStorageDeclarationsParser().parse();
			return new OptionalLocalStorageDeclaration(localToken, stoDecl, repOptStoDecl);
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
	}
	
}
