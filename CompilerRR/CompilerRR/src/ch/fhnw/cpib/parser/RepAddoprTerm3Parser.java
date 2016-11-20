package ch.fhnw.cpib.parser;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.cst.CSTNode;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class RepAddoprTerm3Parser extends AbstractParser {

	public RepAddoprTerm3Parser() {
		super();
	}
	
	public List<CSTNode> parse() throws GrammarError {
		List<CSTNode> list = new LinkedList<CSTNode>();
		if (terminal == Terminals.ADDOPR) {
			list.add(new CSTNode(consume(Terminals.ADDOPR)));
			list.add(new CSTNode("Term3", new Term3Parser().parse()));
			list.add(new CSTNode("RepAddoprTerm3", new RepAddoprTerm3Parser().parse()));
		} 
		else if(terminal == Terminals.RPAREN){
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
			// TODO: leer?
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
		return list;
	}
	
}
