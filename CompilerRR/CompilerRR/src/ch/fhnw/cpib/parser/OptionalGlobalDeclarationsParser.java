package ch.fhnw.cpib.parser;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.cst.CSTNode;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class OptionalGlobalDeclarationsParser extends AbstractParser {

	public OptionalGlobalDeclarationsParser() {
		super();
	}

	@Override
	public List<CSTNode> parse() throws GrammarError {
		List<CSTNode> list = new LinkedList<CSTNode>();
		if (terminal == Terminals.DO) {
			// TODO: leer?
		}
		else if (terminal == Terminals.GLOBAL) {
			list.add(new CSTNode(consume(Terminals.GLOBAL)));
			list.add(new CSTNode("Declarations", new DeclarationsParser().parse()));
		} 
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString() + " terminal found: " + terminal, 0);
		}
		return list;
	}

}