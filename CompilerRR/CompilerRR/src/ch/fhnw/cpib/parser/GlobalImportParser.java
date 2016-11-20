package ch.fhnw.cpib.parser;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.cst.CSTNode;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class GlobalImportParser extends AbstractParser {

	public GlobalImportParser() {
		super();
	}

	@Override
	public List<CSTNode> parse() throws GrammarError {
		List<CSTNode> list = new LinkedList<CSTNode>();
		if (terminal == Terminals.IDENT) {
			list.add(new CSTNode("OptionalFlowMode", new OptionalFlowModeParser().parse()));
			list.add(new CSTNode("OptionalChangemode", new OptionalChangemodeParser().parse()));
			list.add(new CSTNode(consume(Terminals.IDENT)));
		} 
		else if (terminal == Terminals.CHANGEMODE) {
			list.add(new CSTNode("OptionalFlowMode", new OptionalFlowModeParser().parse()));
			list.add(new CSTNode("OptionalChangemode", new OptionalChangemodeParser().parse()));
			list.add(new CSTNode(consume(Terminals.IDENT)));
		} 
		else if (terminal == Terminals.FLOWMODE) {
			list.add(new CSTNode("OptionalFlowMode", new OptionalFlowModeParser().parse()));
			list.add(new CSTNode("OptionalChangemode", new OptionalChangemodeParser().parse()));
			list.add(new CSTNode(consume(Terminals.IDENT)));
		} 
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString() + " terminal found: " + terminal, 0);
		}
		return list;
	}

}
