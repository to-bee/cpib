package ch.fhnw.cpib.parser;

import ch.fhnw.cpib.compiler.classes.OptionalGlobalInits;
import ch.fhnw.cpib.compiler.classes.OptionalGlobalInitsEps;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class OptionalGlobalInitsParser extends AbstractParser {

	public OptionalGlobalInitsParser() {
		super();
	}
	
	public IConcSyn.IOptionalGlobalInits parse() throws GrammarError {
		if (terminal == Terminals.ENDPROC) {
			return new OptionalGlobalInitsEps();
		} 
		else if(terminal == Terminals.ENDFUN){
			return new OptionalGlobalInitsEps();
		}
		else if(terminal == Terminals.ENDSWITCH){
			return new OptionalGlobalInitsEps();
		}
		else if(terminal == Terminals.CASEDEFAULT){
			return new OptionalGlobalInitsEps();
		}
		else if(terminal == Terminals.CASE){
			return new OptionalGlobalInitsEps();
		}
		else if(terminal == Terminals.ENDWHILE){
			return new OptionalGlobalInitsEps();
		}
		else if(terminal == Terminals.ENDIF){
			return new OptionalGlobalInitsEps();
		}
		else if(terminal == Terminals.ELSE){
			return new OptionalGlobalInitsEps();
		}
		else if(terminal == Terminals.ENDPROGRAM){
			return new OptionalGlobalInitsEps();
		}
		else if(terminal == Terminals.SEMICOLON){
			return new OptionalGlobalInitsEps();
		}
		else if(terminal == Terminals.INIT){
			Token init = consume(Terminals.INIT);
			IConcSyn.IIdents idents = new IdentsParser().parse();
			return new OptionalGlobalInits(init, idents);
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
	}
	
}
