package ch.fhnw.cpib.compiler.parser;

import ch.fhnw.cpib.compiler.cst.classes.RepAddOprTerm3;
import ch.fhnw.cpib.compiler.cst.classes.RepAddOprTerm3Eps;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class RepAddoprTerm3Parser extends AbstractParser {

	public RepAddoprTerm3Parser() {
		super();
	}
	
	public IConcSyn.IRepADDOPRterm3 parse() throws GrammarError {
		if (terminal == Terminals.ADDOPR) {
			Token addToken = consume(Terminals.ADDOPR);
			IConcSyn.ITerm3 t3 = new Term3Parser().parse();
			IConcSyn.IRepADDOPRterm3 repAddOprT3 = new RepAddoprTerm3Parser().parse();
			return new RepAddOprTerm3(addToken, t3, repAddOprT3);
		} 
		else if(terminal == Terminals.RPAREN){
			return new RepAddOprTerm3Eps();
		}
		else if(terminal == Terminals.COMMA){
			return new RepAddOprTerm3Eps();
		}
		else if(terminal == Terminals.DO){
			return new RepAddOprTerm3Eps();
		}
		else if(terminal == Terminals.THEN){
			return new RepAddOprTerm3Eps();
		}
		else if(terminal == Terminals.ENDPROC){
			return new RepAddOprTerm3Eps();
		}
		else if(terminal == Terminals.ENDFUN){
			return new RepAddOprTerm3Eps();
		}
		else if(terminal == Terminals.ENDSWITCH){
			return new RepAddOprTerm3Eps();
		}
		else if(terminal == Terminals.CASEDEFAULT){
			return new RepAddOprTerm3Eps();
		}
		else if(terminal == Terminals.CASE){
			return new RepAddOprTerm3Eps();
		}
		else if(terminal == Terminals.ENDWHILE){
			return new RepAddOprTerm3Eps();
		}
		else if(terminal == Terminals.ENDIF){
			return new RepAddOprTerm3Eps();
		}
		else if(terminal == Terminals.ELSE){
			return new RepAddOprTerm3Eps();
		}
		else if(terminal == Terminals.ENDPROGRAM){
			return new RepAddOprTerm3Eps();
		}
		else if(terminal == Terminals.SEMICOLON){
			return new RepAddOprTerm3Eps();
		}
		else if(terminal == Terminals.BECOMES){
			return new RepAddOprTerm3Eps();
		}
		else if(terminal == Terminals.BOOLOPR){
			return new RepAddOprTerm3Eps();
		}
		else if(terminal == Terminals.RELOPR){
			return new RepAddOprTerm3Eps();
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
	}
	
}
