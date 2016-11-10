package ch.fhnw.cpib.parser;

import java.util.LinkedList;

import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class repMultoprFactorParser extends AbstractParser {

	public repMultoprFactorParser(LinkedList<Token> tokenlist) {
		super(tokenlist);
	}
	
	public void parse() throws GrammarError {
		if(terminal == Terminals.RPAREN){
			//TODO: stimmt es, dass dies einfach leer ist?
		}
		else if(terminal == Terminals.COMMA){
			//TODO: stimmt es, dass dies einfach leer ist?
		}
		else if(terminal == Terminals.DO){
			//TODO: stimmt es, dass dies einfach leer ist?
		}
		else if(terminal == Terminals.THEN){
			//TODO: stimmt es, dass dies einfach leer ist?
		}
		else if(terminal == Terminals.ENDPROC){
			//TODO: stimmt es, dass dies einfach leer ist?
		}
		else if(terminal == Terminals.ENDFUN){
			//TODO: stimmt es, dass dies einfach leer ist?
		}
		else if(terminal == Terminals.ENDSWITCH){
			//TODO: stimmt es, dass dies einfach leer ist?
		}
		else if(terminal == Terminals.CASEDEFAULT){
			//TODO: stimmt es, dass dies einfach leer ist?
		}
		else if(terminal == Terminals.CASE){
			//TODO: stimmt es, dass dies einfach leer ist?
		}
		else if(terminal == Terminals.ENDWHILE){
			//TODO: stimmt es, dass dies einfach leer ist?
		}
		else if(terminal == Terminals.ENDIF){
			//TODO: stimmt es, dass dies einfach leer ist?
		}
		else if(terminal == Terminals.ELSE){
			//TODO: stimmt es, dass dies einfach leer ist?
		}
		else if(terminal == Terminals.ENDPROGRAM){
			//TODO: stimmt es, dass dies einfach leer ist?
		}
		else if(terminal == Terminals.SEMICOLON){
			//TODO: stimmt es, dass dies einfach leer ist?
		}
		else if(terminal == Terminals.BECOMES){
			//TODO: stimmt es, dass dies einfach leer ist?
		}
		else if(terminal == Terminals.BOOLOPR){
			//TODO: stimmt es, dass dies einfach leer ist?
		}
		else if(terminal == Terminals.RELOPR){
			//TODO: stimmt es, dass dies einfach leer ist?
		}
		else if(terminal == Terminals.ADDOPR){
			// TODO: leer?
		}
		else if(terminal == Terminals.MULTOPR){
			consume(Terminals.MULTOPR);
			new FactorParser().parse();
			new RepMultoprFactorParser().parse();
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
	}
	
}
