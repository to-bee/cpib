package ch.fhnw.cpib.parser;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.cst.CSTNode;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class TypeIdentParser extends AbstractParser {

	public TypeIdentParser() {
		super();
	}

	@Override
	public List<CSTNode> parse() throws GrammarError {
		List<CSTNode> list = new LinkedList<CSTNode>();
		if (terminal == Terminals.IDENT) {
			list.add(new CSTNode(consume(Terminals.IDENT)));
			list.add(new CSTNode(consume(Terminals.COLON)));
			list.add(new CSTNode("TypeDeclaration", new TypeDeclarationParser().parse()));
		} 
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
		return list;
	}

}
