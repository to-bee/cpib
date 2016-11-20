package ch.fhnw.cpib.parser;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.cst.CSTNode;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class DeclarationsParser extends AbstractParser {

	public DeclarationsParser() {
		super();
	}

	@Override
	public List<CSTNode> parse() throws GrammarError {
		List<CSTNode> list = new LinkedList<CSTNode>();
		if (terminal == Terminals.PROC) {
			list.add(new CSTNode("Declaration", new DeclarationParser().parse()));
			list.add(new CSTNode("RepeatingOptionalDeclarations", new RepeatingOptionalDeclarationsParser().parse()));
		}
		else if (terminal == Terminals.FUN) {
			list.add(new CSTNode("Declaration", new DeclarationParser().parse()));
			list.add(new CSTNode("RepeatingOptionalDeclarations", new RepeatingOptionalDeclarationsParser().parse()));
		}
		else if (terminal == Terminals.IDENT) {
			list.add(new CSTNode("Declaration", new DeclarationParser().parse()));
			list.add(new CSTNode("RepeatingOptionalDeclarations", new RepeatingOptionalDeclarationsParser().parse()));
		}
		else if (terminal == Terminals.CHANGEMODE) {
			list.add(new CSTNode("Declaration", new DeclarationParser().parse()));
			list.add(new CSTNode("RepeatingOptionalDeclarations", new RepeatingOptionalDeclarationsParser().parse()));
		}
		else {
			System.out.println(tokenlist.toString());
			throw new GrammarError("GrammarError at: "+ this.getClass().toString() + " terminal found: " + terminal, 0);
		}
		return list;
		
	}

}
