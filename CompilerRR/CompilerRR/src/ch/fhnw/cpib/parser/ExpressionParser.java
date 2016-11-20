package ch.fhnw.cpib.parser;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.cst.CSTNode;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class ExpressionParser extends AbstractParser {

	public ExpressionParser() {
		super();
	}

	@Override
	public List<CSTNode> parse() throws GrammarError {
		List<CSTNode> list = new LinkedList<CSTNode>();
		if (terminal == Terminals.LPAREN) {
			list.add(new CSTNode("Term1", new Term1Parser().parse()));
			list.add(new CSTNode("RepBooloprTerm1", new RepBooloprTerm1Parser().parse()));
		} 
		else if (terminal == Terminals.ADDOPR) {
			list.add(new CSTNode("Term1", new Term1Parser().parse()));
			list.add(new CSTNode("RepBooloprTerm1", new RepBooloprTerm1Parser().parse()));
		}
		else if (terminal == Terminals.NOTOPER) {
			list.add(new CSTNode("Term1", new Term1Parser().parse()));
			list.add(new CSTNode("RepBooloprTerm1", new RepBooloprTerm1Parser().parse()));
		} 
		else if (terminal == Terminals.IDENT) {
			list.add(new CSTNode("Term1", new Term1Parser().parse()));
			list.add(new CSTNode("RepBooloprTerm1", new RepBooloprTerm1Parser().parse()));
		} 
		else if (terminal == Terminals.LITERAL) {
			list.add(new CSTNode("Term1", new Term1Parser().parse()));
			list.add(new CSTNode("RepBooloprTerm1", new RepBooloprTerm1Parser().parse()));
		} 
		else {
			System.out.println(tokenlist.toString());
			throw new GrammarError("GrammarError at: "+ this.getClass().toString() + " terminal found: " + terminal, 0);
		}
		return list;
	}

}
