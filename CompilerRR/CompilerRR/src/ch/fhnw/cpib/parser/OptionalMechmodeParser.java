package ch.fhnw.cpib.parser;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.cst.CSTNode;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class OptionalMechmodeParser extends AbstractParser {

	public OptionalMechmodeParser() {
		super();
	}

	@Override
	public List<CSTNode> parse() throws GrammarError {
		List<CSTNode> list = new LinkedList<CSTNode>();
		if (terminal == Terminals.MECHMODE) {
			list.add(new CSTNode(consume(Terminals.MECHMODE)));
		}
		else if (terminal == Terminals.IDENT) {
			// TODO: leer?
		} 
		else if (terminal == Terminals.CHANGEMODE) {
			// TODO: leer?
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
		return list;
	}

}
