package ch.fhnw.cpib.parser;

import ch.fhnw.cpib.compiler.cst.classes.DeclarationFunction;
import ch.fhnw.cpib.compiler.cst.classes.DeclarationProcedure;
import ch.fhnw.cpib.compiler.cst.classes.DeclarationStorage;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class DeclarationParser extends AbstractParser {

	public DeclarationParser() {
		super();
	}

	public IConcSyn.IDeclaration parse() throws GrammarError {
		if (terminal == Terminals.IDENT) {
			IConcSyn.IStorageDeclaration stoDecl = new StorageDeclarationParser().parse();
			return new DeclarationStorage(stoDecl);
		} 
		else if (terminal == Terminals.CHANGEMODE) {
			IConcSyn.IStorageDeclaration stoDecl = new StorageDeclarationParser().parse();
			return new DeclarationStorage(stoDecl);
		}
		else if (terminal == Terminals.FUN) {
			IConcSyn.IFunctionDeclaration funDec = new FunctionDeclarationParser().parse();
			return new DeclarationFunction(funDec);
		} 
		else if (terminal == Terminals.PROC) {
			IConcSyn.IProcedureDeclaration procDec = new ProcedureDeclarationParser().parse();
			return new DeclarationProcedure(procDec);
		} 
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString() + " terminal found: " + terminal, 0);
		}
	}

}
