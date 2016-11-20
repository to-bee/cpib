package ch.fhnw.cpib.parser;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.cst.CSTNode;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class OptionalFlowModeParser extends AbstractParser {

	public OptionalFlowModeParser() {
		super();
	}

	@Override
	public List<CSTNode> parse() throws GrammarError {
		List<CSTNode> list = new LinkedList<CSTNode>();
		if (terminal == Terminals.MECHMODE) {
			// TODO: leer?
		}
		else if (terminal == Terminals.IDENT) {
			// TODO: leer?
		} 
		else if (terminal == Terminals.CHANGEMODE) {
			// TODO: leer?
		}
		else if (terminal == Terminals.FLOWMODE) {
			list.add(new CSTNode(consume(Terminals.FLOWMODE)));
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString() + " terminal found: " + terminal, 0);
		}
		return list;
	}

}
