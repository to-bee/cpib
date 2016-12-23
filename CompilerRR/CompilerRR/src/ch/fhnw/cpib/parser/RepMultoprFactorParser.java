package ch.fhnw.cpib.parser;

import ch.fhnw.cpib.compiler.classes.RepMultOprFactor;
import ch.fhnw.cpib.compiler.classes.RepMultOprFactorEps;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class RepMultoprFactorParser extends AbstractParser {

	public RepMultoprFactorParser() {
		super();
	}
	
	public IConcSyn.IRepMULTOPRfactor parse() throws GrammarError {
		if(terminal == Terminals.RPAREN){
			return new RepMultOprFactorEps();
		}
		else if(terminal == Terminals.COMMA){
			return new RepMultOprFactorEps();
		}
		else if(terminal == Terminals.DO){
			return new RepMultOprFactorEps();
		}
		else if(terminal == Terminals.THEN){
			return new RepMultOprFactorEps();
		}
		else if(terminal == Terminals.ENDPROC){
			return new RepMultOprFactorEps();
		}
		else if(terminal == Terminals.ENDFUN){
			return new RepMultOprFactorEps();
		}
		else if(terminal == Terminals.ENDSWITCH){
			return new RepMultOprFactorEps();
		}
		else if(terminal == Terminals.CASEDEFAULT){
			return new RepMultOprFactorEps();
		}
		else if(terminal == Terminals.CASE){
			return new RepMultOprFactorEps();
		}
		else if(terminal == Terminals.ENDWHILE){
			return new RepMultOprFactorEps();
		}
		else if(terminal == Terminals.ENDIF){
			return new RepMultOprFactorEps();
		}
		else if(terminal == Terminals.ELSE){
			return new RepMultOprFactorEps();
		}
		else if(terminal == Terminals.ENDPROGRAM){
			return new RepMultOprFactorEps();
		}
		else if(terminal == Terminals.SEMICOLON){
			return new RepMultOprFactorEps();
		}
		else if(terminal == Terminals.BECOMES){
			return new RepMultOprFactorEps();
		}
		else if(terminal == Terminals.BOOLOPR){
			return new RepMultOprFactorEps();
		}
		else if(terminal == Terminals.RELOPR){
			return new RepMultOprFactorEps();
		}
		else if(terminal == Terminals.ADDOPR){
			return new RepMultOprFactorEps();
		}
		else if(terminal == Terminals.MULTOPR){
			Token multOpr = consume(Terminals.MULTOPR);
			IConcSyn.IFactor factor = new FactorParser().parse();
			IConcSyn.IRepMULTOPRfactor repMulOprFac = new RepMultoprFactorParser().parse();
			return new RepMultOprFactor(multOpr, factor, repMulOprFac);
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
	}
	
}
