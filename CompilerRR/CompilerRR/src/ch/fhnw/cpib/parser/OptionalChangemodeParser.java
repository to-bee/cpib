package ch.fhnw.cpib.parser;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.cst.CSTNode;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class OptionalChangemodeParser extends AbstractParser {

	public OptionalChangemodeParser() {
		super();
	}

	@Override
	public List<CSTNode> parse() throws GrammarError {
		List<CSTNode> list = new LinkedList<CSTNode>();
		if (terminal == Terminals.IDENT) {
			// TODO: leer?
		} 
		else if (terminal == Terminals.CHANGEMODE) {
			list.add(new CSTNode(consume(Terminals.CHANGEMODE)));
		}
		else {
			System.out.println(tokenlist.toString());
			System.out.println(token);
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
		return list;
	}

}
