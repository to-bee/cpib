package ch.fhnw.cpib.parser;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.cst.CSTNode;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class RepeatingOptionalGlobalImportsParser extends AbstractParser {

	public RepeatingOptionalGlobalImportsParser() {
		super();
	}

	@Override
	public List<CSTNode> parse() throws GrammarError {
		List<CSTNode> list = new LinkedList<CSTNode>();
		if (terminal == Terminals.DO) {
			// TODO: leer?
		} 
		else if (terminal == Terminals.LOCAL) {
			// TODO: leer?
		} 
		else if (terminal == Terminals.COMMA) {
			list.add(new CSTNode(consume(Terminals.COMMA)));
			list.add(new CSTNode("GlobalImport", new GlobalImportParser().parse()));
			list.add(new CSTNode("RepeatingOptionalGlobalImports", new RepeatingOptionalGlobalImportsParser().parse()));
		} 
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
		return list;
	}

}
