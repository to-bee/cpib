package ch.fhnw.cpib.compiler.parser;

import ch.fhnw.cpib.compiler.cst.classes.ProcedureDeclaration;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class ProcedureDeclarationParser extends AbstractParser {

	public ProcedureDeclarationParser() {
		super();
	}

	public IConcSyn.IProcedureDeclaration parse() throws GrammarError {
		if (terminal == Terminals.PROC) {
			Token proc = consume(Terminals.PROC);
			Token ident = consume(Terminals.IDENT);
			IConcSyn.IParameterList parList = new ParameterListParser().parse();
			IConcSyn.IOptionalGlobalImports optGlobImp = new OptionalGlobalImportsParser().parse();
			IConcSyn.IOptionalLocalStorageDeclarations optLocStoDec = new OptionalLocalStorageDeclarationsParser().parse();
			Token doToken = consume(Terminals.DO);
			IConcSyn.IBlockCmd blockCmd = new BlockCmdParser().parse();
			Token endProc = consume(Terminals.ENDPROC);
			return new ProcedureDeclaration(proc, ident, parList, optGlobImp, optLocStoDec, doToken, blockCmd, endProc);
		} 
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
	}

}
