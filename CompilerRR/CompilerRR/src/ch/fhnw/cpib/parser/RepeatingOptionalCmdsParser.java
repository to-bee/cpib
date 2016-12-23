package ch.fhnw.cpib.parser;

import ch.fhnw.cpib.compiler.classes.RepeatingOptionalCmds;
import ch.fhnw.cpib.compiler.classes.RepeatingOptionalCmdsEps;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class RepeatingOptionalCmdsParser extends AbstractParser {

	public RepeatingOptionalCmdsParser() {
		super();
	}
	
	public IConcSyn.IRepeatingOptionalCmds parse() throws GrammarError {
		if (terminal == Terminals.ENDPROC) {
			return new RepeatingOptionalCmdsEps();
		} 
		else if(terminal == Terminals.ENDFUN){
			return new RepeatingOptionalCmdsEps();
		}
		else if(terminal == Terminals.ENDSWITCH){
			return new RepeatingOptionalCmdsEps();
		}
		else if(terminal == Terminals.CASEDEFAULT){
			return new RepeatingOptionalCmdsEps();
		}
		else if(terminal == Terminals.CASE){
			return new RepeatingOptionalCmdsEps();
		}
		else if(terminal == Terminals.ENDWHILE){
			return new RepeatingOptionalCmdsEps();
		}
		else if(terminal == Terminals.ENDIF){
			return new RepeatingOptionalCmdsEps();
		}
		else if(terminal == Terminals.ELSE){
			return new RepeatingOptionalCmdsEps();
		}
		else if(terminal == Terminals.ENDPROGRAM){
			return new RepeatingOptionalCmdsEps();
		}
		else if(terminal == Terminals.SEMICOLON){
			Token semicolon = consume(Terminals.SEMICOLON);
			IConcSyn.ICmd cmd = new CmdParser().parse();
			IConcSyn.IRepeatingOptionalCmds repOptCmds = new RepeatingOptionalCmdsParser().parse();
			return new RepeatingOptionalCmds(semicolon, cmd, repOptCmds);
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
	}
	
}
