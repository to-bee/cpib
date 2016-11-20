package ch.fhnw.cpib.parser;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.cst.CSTNode;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class MonadicOperatorParser extends AbstractParser {

	public MonadicOperatorParser() {
		super();
	}
	
	public List<CSTNode> parse() throws GrammarError {
		List<CSTNode> list = new LinkedList<CSTNode>();
		if (terminal == Terminals.NOTOPER) {
			list.add(new CSTNode(consume(Terminals.NOTOPER)));
		} 
		else if(terminal == Terminals.ADDOPR){
			list.add(new CSTNode(consume(Terminals.ADDOPR)));
		}
		else {
			System.out.println(tokenlist.toString());
			throw new GrammarError("GrammarError at: "+ this.getClass().toString() + " terminal found: " + terminal, 0);
		}
		return list;
	}
	
}
