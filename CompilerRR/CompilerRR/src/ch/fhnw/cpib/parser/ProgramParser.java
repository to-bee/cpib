package ch.fhnw.cpib.parser;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class ProgramParser extends AbstractParser{
	
	
	public ProgramParser(LinkedList<Token> tokenlist) {
		super(tokenlist);
	}
	
	public void parse() throws GrammarError {
		if (terminal == Terminals.PROGRAM) {
			consume(Terminals.PROGRAM);
			consume(Terminals.IDENT);
			new ProgramParameterListParser().parse();
			new OptionalGlobalDeclarationsParser().parse();
			consume(Terminals.DO);
			new BlockCmdParser().parse();
			consume(Terminals.ENDPROGRAM);
		} else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
	}
	
	
	
}
