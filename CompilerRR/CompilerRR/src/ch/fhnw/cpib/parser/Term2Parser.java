package ch.fhnw.cpib.parser;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.cst.CSTNode;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class Term2Parser extends AbstractParser {

	public Term2Parser() {
		super();
	}
	
	public List<CSTNode> parse() throws GrammarError {
		List<CSTNode> list = new LinkedList<CSTNode>();
		if (terminal == Terminals.LPAREN) {
			list.add(new CSTNode("Term3", new Term3Parser().parse()));
			list.add(new CSTNode("RepAddoprTerm3", new RepAddoprTerm3Parser().parse()));
		} 
		else if(terminal == Terminals.ADDOPR){
			list.add(new CSTNode("Term3", new Term3Parser().parse()));
			list.add(new CSTNode("RepAddoprTerm3", new RepAddoprTerm3Parser().parse()));
		}
		else if(terminal == Terminals.NOTOPER){
			list.add(new CSTNode("Term3", new Term3Parser().parse()));
			list.add(new CSTNode("RepAddoprTerm3", new RepAddoprTerm3Parser().parse()));
		}
		else if(terminal == Terminals.IDENT){
			list.add(new CSTNode("Term3", new Term3Parser().parse()));
			list.add(new CSTNode("RepAddoprTerm3", new RepAddoprTerm3Parser().parse()));
		}
		else if(terminal == Terminals.LITERAL){
			list.add(new CSTNode("Term3", new Term3Parser().parse()));
			list.add(new CSTNode("RepAddoprTerm3", new RepAddoprTerm3Parser().parse()));
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
		return list;
	}
	
}