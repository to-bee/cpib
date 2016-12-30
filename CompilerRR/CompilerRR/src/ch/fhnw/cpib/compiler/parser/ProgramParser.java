package ch.fhnw.cpib.compiler.parser;

import java.util.LinkedList;

import ch.fhnw.cpib.compiler.cst.classes.Program;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class ProgramParser extends AbstractParser{
	
	
	public ProgramParser(LinkedList<Token> tokenlist) {
		super(tokenlist);
	}
	
	public IConcSyn.IProgram parse() throws GrammarError {
		if (terminal == Terminals.PROGRAM) {
			Token program = consume(Terminals.PROGRAM);
			Token ident = consume(Terminals.IDENT);
			IConcSyn.IProgamParameterList proParList = new ProgramParameterListParser().parse();
			IConcSyn.IOptionalGlobalDeclarations optGlobDecl = new OptionalGlobalDeclarationsParser().parse();
			Token doToken = consume(Terminals.DO);
			IConcSyn.IBlockCmd blockCmd = new BlockCmdParser().parse();
			Token endProgram = consume(Terminals.ENDPROGRAM);
			return new Program(program, ident, proParList, optGlobDecl, doToken, blockCmd, endProgram);
		} else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
	}
	
	
	
}
