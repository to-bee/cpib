package ch.fhnw.cpib.parser;

import ch.fhnw.cpib.compiler.cst.classes.RepeatingOptionalIdents;
import ch.fhnw.cpib.compiler.cst.classes.RepeatingOptionalIdentsEps;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class RepeatingOptionalIdentsParser extends AbstractParser {

	public RepeatingOptionalIdentsParser() {
		super();
	}
	
	public IConcSyn.IRepeatingOptionalIdents parse() throws GrammarError {
		if (terminal == Terminals.ENDPROC) {
			return new RepeatingOptionalIdentsEps();
		} 
		else if(terminal == Terminals.ENDFUN){
			return new RepeatingOptionalIdentsEps();
		}
		else if(terminal == Terminals.ENDSWITCH){
			return new RepeatingOptionalIdentsEps();
		}
		else if(terminal == Terminals.CASEDEFAULT){
			return new RepeatingOptionalIdentsEps();
		}
		else if(terminal == Terminals.CASE){
			return new RepeatingOptionalIdentsEps();
		}
		else if(terminal == Terminals.ENDWHILE){
			return new RepeatingOptionalIdentsEps();
		}
		else if(terminal == Terminals.ENDIF){
			return new RepeatingOptionalIdentsEps();
		}
		else if(terminal == Terminals.ELSE){
			return new RepeatingOptionalIdentsEps();
		}
		else if(terminal == Terminals.ENDPROGRAM){
			return new RepeatingOptionalIdentsEps();
		}
		else if(terminal == Terminals.SEMICOLON){
			return new RepeatingOptionalIdentsEps();
		}
		else if(terminal == Terminals.COMMA){
			Token comma = consume(Terminals.COMMA);
			Token ident = consume(Terminals.IDENT);
			IConcSyn.IIdents idents = new IdentsParser().parse();
			return new RepeatingOptionalIdents(comma, ident, idents);
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
	}
	
}
