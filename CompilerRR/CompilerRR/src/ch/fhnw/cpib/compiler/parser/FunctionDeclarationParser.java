package ch.fhnw.cpib.compiler.parser;

import ch.fhnw.cpib.compiler.cst.classes.FunctionDeclaration;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class FunctionDeclarationParser extends AbstractParser {

	public FunctionDeclarationParser() {
		super();
	}

	public IConcSyn.IFunctionDeclaration parse() throws GrammarError {
		if (terminal == Terminals.FUN) {
			Token funToken = consume(Terminals.FUN);
			Token identToken = consume(Terminals.IDENT);
			IConcSyn.IParameterList parList = new ParameterListParser().parse();
			Token returnsToken = consume(Terminals.RETURNS);
			IConcSyn.IStorageDeclaration stoDecl = new StorageDeclarationParser().parse();
			IConcSyn.IOptionalGlobalImports globImp = new OptionalGlobalImportsParser().parse();
			IConcSyn.IOptionalLocalStorageDeclarations optLoc = new OptionalLocalStorageDeclarationsParser().parse();
			Token doToken = consume(Terminals.DO);
			IConcSyn.IBlockCmd blockCmd = new BlockCmdParser().parse();
			Token endFun = consume(Terminals.ENDFUN);
			return new FunctionDeclaration(funToken, identToken, parList, returnsToken, stoDecl, globImp, optLoc, doToken, blockCmd, endFun);
		} 
		else {
			System.out.println(tokenlist.toString());
			throw new GrammarError("GrammarError at: "+ this.getClass().toString() + " terminal found: " + terminal, 0);
		}
	}

}
