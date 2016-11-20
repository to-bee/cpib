package ch.fhnw.cpib.parser;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.cst.CSTNode;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class FactorParser extends AbstractParser {

	public FactorParser() {
		super();
	}
	
	public List<CSTNode> parse() throws GrammarError {
		List<CSTNode> list = new LinkedList<CSTNode>();
		if (terminal == Terminals.LITERAL) {
			list.add(new CSTNode(consume(Terminals.LITERAL)));
		} 
		else if(terminal == Terminals.IDENT){
			list.add(new CSTNode(consume(Terminals.IDENT)));
			list.add(new CSTNode("OptionalIdent", new OptionalIdentParser().parse()));
		}
		else if(terminal == Terminals.ADDOPR){
			list.add(new CSTNode("MonadicOperator", new MonadicOperatorParser().parse()));
			list.add(new CSTNode("Factor", new FactorParser().parse()));
		}
		else if(terminal == Terminals.NOTOPER){
			list.add(new CSTNode("MonadicOperator", new MonadicOperatorParser().parse()));
			list.add(new CSTNode("Factor", new FactorParser().parse()));
		}
		else if(terminal == Terminals.LPAREN){
			list.add(new CSTNode(consume(Terminals.LPAREN)));
			list.add(new CSTNode("Expression", new ExpressionParser().parse()));
			list.add(new CSTNode(consume(Terminals.RPAREN)));
		}
		else {
			System.out.println(tokenlist.toString());
			throw new GrammarError("GrammarError at: "+ this.getClass().toString() + " terminal found: " + terminal, 0);
		}
		return list;
	}
	
}
