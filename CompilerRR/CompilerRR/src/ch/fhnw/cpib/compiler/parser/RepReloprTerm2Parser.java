package ch.fhnw.cpib.compiler.parser;

import ch.fhnw.cpib.compiler.cst.classes.RepRelOprTerm2;
import ch.fhnw.cpib.compiler.cst.classes.RepRelOprTerm2Eps;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class RepReloprTerm2Parser extends AbstractParser {

	public RepReloprTerm2Parser() {
		super();
	}
	
	public IConcSyn.IRepRELOPRterm2 parse() throws GrammarError {
		if (terminal == Terminals.RPAREN) {
			return new RepRelOprTerm2Eps();
		} 
		else if(terminal == Terminals.COMMA){
			return new RepRelOprTerm2Eps();
		}
		else if(terminal == Terminals.DO){
			return new RepRelOprTerm2Eps();
		}
		else if(terminal == Terminals.THEN){
			return new RepRelOprTerm2Eps();
		}
		else if(terminal == Terminals.ENDPROC){
			return new RepRelOprTerm2Eps();
		}
		else if(terminal == Terminals.ENDFUN){
			return new RepRelOprTerm2Eps();
		}
		else if(terminal == Terminals.ENDSWITCH){
			return new RepRelOprTerm2Eps();
		}
		else if(terminal == Terminals.CASEDEFAULT){
			return new RepRelOprTerm2Eps();
		}
		else if(terminal == Terminals.CASE){
			return new RepRelOprTerm2Eps();
		}
		else if(terminal == Terminals.ENDWHILE){
			return new RepRelOprTerm2Eps();
		}
		else if(terminal == Terminals.ENDIF){
			return new RepRelOprTerm2Eps();
		}
		else if(terminal == Terminals.ELSE){
			return new RepRelOprTerm2Eps();
		}
		else if(terminal == Terminals.ENDPROGRAM){
			return new RepRelOprTerm2Eps();
		}
		else if(terminal == Terminals.SEMICOLON){
			return new RepRelOprTerm2Eps();
		}
		else if(terminal == Terminals.BECOMES){
			return new RepRelOprTerm2Eps();
		}
		else if(terminal == Terminals.BOOLOPR){
			return new RepRelOprTerm2Eps();
		}
		else if(terminal == Terminals.RELOPR){
			Token relOpr = consume(Terminals.RELOPR);
			IConcSyn.ITerm2 t2 = new Term2Parser().parse();
			IConcSyn.IRepRELOPRterm2 repRelOprT2 = new RepReloprTerm2Parser().parse();
			return new RepRelOprTerm2(relOpr, t2, repRelOprT2);
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
	}
	
}
