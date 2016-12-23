package ch.fhnw.cpib.parser;

import ch.fhnw.cpib.compiler.classes.RepeatingOptionalCase;
import ch.fhnw.cpib.compiler.classes.RepeatingOptionalCaseEps;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class RepeatingOptionalCaseParser extends AbstractParser {

	public RepeatingOptionalCaseParser() {
		super();
	}
	
	public IConcSyn.IRepeatingOptionalCase parse() throws GrammarError {
		if (terminal == Terminals.CASEDEFAULT) {
			return new RepeatingOptionalCaseEps();
			
		} 
		else if(terminal == Terminals.CASE){
			Token caseToken = consume(Terminals.CASE);
			Token literal = consume(Terminals.LITERAL);
			Token colonToken = consume(Terminals.COLON);
			IConcSyn.IBlockCmd blockCmd = new BlockCmdParser().parse();
			IConcSyn.IRepeatingOptionalCase reptOptCase = new RepeatingOptionalCaseParser().parse();
			return new RepeatingOptionalCase(caseToken, literal, colonToken, blockCmd, reptOptCase);
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
	}
	
}
