package ch.fhnw.cpib.parser;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.cst.CSTNode;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class RepeatingOptionalCaseParser extends AbstractParser {

	public RepeatingOptionalCaseParser() {
		super();
	}
	
	public List<CSTNode> parse() throws GrammarError {
		List<CSTNode> list = new LinkedList<CSTNode>();
		if (terminal == Terminals.CASEDEFAULT) {
			//TODO: stimmt es, dass dies einfach leer ist?
			
		} 
		else if(terminal == Terminals.CASE){
			list.add(new CSTNode(consume(Terminals.CASE)));
			list.add(new CSTNode(consume(Terminals.LITERAL)));
			list.add(new CSTNode(consume(Terminals.COLON)));
			list.add(new CSTNode("BlockCmd", new BlockCmdParser().parse()));
			list.add(new CSTNode("RepeatingOptionalCase", new RepeatingOptionalCaseParser().parse()));
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
		return list;
	}
	
}
