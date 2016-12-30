package ch.fhnw.cpib.compiler.parser;

import ch.fhnw.cpib.compiler.cst.classes.RepeatingOptionalStorageDeclarations;
import ch.fhnw.cpib.compiler.cst.classes.RepeatingOptionalStorageDeclarationsEps;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class RepeatingOptionalStorageDeclarationsParser extends AbstractParser {

	public RepeatingOptionalStorageDeclarationsParser() {
		super();
	}
	
	public IConcSyn.IRepeatingOptionalStorageDeclarations parse() throws GrammarError {
		if (terminal == Terminals.DO) {
			return new RepeatingOptionalStorageDeclarationsEps();
		} 
		else if(terminal == Terminals.SEMICOLON){
			Token semicolon = consume(Terminals.SEMICOLON);
			IConcSyn.IStorageDeclaration stoDecl = new StorageDeclarationParser().parse();
			IConcSyn.IRepeatingOptionalStorageDeclarations repOptStoDecl = new RepeatingOptionalStorageDeclarationsParser().parse();
			return new RepeatingOptionalStorageDeclarations(semicolon, stoDecl, repOptStoDecl);
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
	}
	
}
