package ch.fhnw.cpib.compiler.parser;

import ch.fhnw.cpib.compiler.cst.classes.RepBoolOprTerm1;
import ch.fhnw.cpib.compiler.cst.classes.RepBoolOprTerm1Eps;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class RepBooloprTerm1Parser extends AbstractParser {

	public RepBooloprTerm1Parser() {
		super();
	}
	
	public IConcSyn.IRepBOOLOPRterm1 parse() throws GrammarError {
		if (terminal == Terminals.RPAREN) {
			return new RepBoolOprTerm1Eps();
		} 
		else if(terminal == Terminals.COMMA){
			return new RepBoolOprTerm1Eps();
		}
		else if(terminal == Terminals.DO){
			return new RepBoolOprTerm1Eps();
		}
		else if(terminal == Terminals.THEN){
			return new RepBoolOprTerm1Eps();
		}
		else if(terminal == Terminals.ENDPROC){
			return new RepBoolOprTerm1Eps();
		}
		else if(terminal == Terminals.ENDFUN){
			return new RepBoolOprTerm1Eps();
		}
		else if(terminal == Terminals.ENDSWITCH){
			return new RepBoolOprTerm1Eps();
		}
		else if(terminal == Terminals.CASEDEFAULT){
			return new RepBoolOprTerm1Eps();
		}
		else if(terminal == Terminals.CASE){
			return new RepBoolOprTerm1Eps();
		}
		else if(terminal == Terminals.ENDWHILE){
			return new RepBoolOprTerm1Eps();
		}
		else if(terminal == Terminals.ENDIF){
			return new RepBoolOprTerm1Eps();
		}
		else if(terminal == Terminals.ELSE){
			return new RepBoolOprTerm1Eps();
		}
		else if(terminal == Terminals.ENDPROGRAM){
			return new RepBoolOprTerm1Eps();
		}
		else if(terminal == Terminals.SEMICOLON){
			return new RepBoolOprTerm1Eps();
		}
		else if(terminal == Terminals.BECOMES){
			return new RepBoolOprTerm1Eps();
		}
		else if(terminal == Terminals.BOOLOPR){
			Token boolOpr = consume(Terminals.BOOLOPR);
			IConcSyn.ITerm1 t1 = new Term1Parser().parse();
			IConcSyn.IRepBOOLOPRterm1 repBoolOprT1 = new RepBooloprTerm1Parser().parse();
			return new RepBoolOprTerm1(boolOpr, t1, repBoolOprT1);
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
	}
	
}
