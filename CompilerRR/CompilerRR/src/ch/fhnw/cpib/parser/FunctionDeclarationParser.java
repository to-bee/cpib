package ch.fhnw.cpib.parser;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.cst.CSTNode;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class FunctionDeclarationParser extends AbstractParser {

	public FunctionDeclarationParser() {
		super();
	}

	@Override
	public List<CSTNode> parse() throws GrammarError {
		List<CSTNode> list = new LinkedList<CSTNode>();
		if (terminal == Terminals.FUN) {
			list.add(new CSTNode(consume(Terminals.FUN)));
			list.add(new CSTNode(consume(Terminals.IDENT)));
			list.add(new CSTNode("ParameterList", new ParameterListParser().parse()));
			list.add(new CSTNode(consume(Terminals.RETURNS)));
			list.add(new CSTNode("StorageDeclaration", new StorageDeclarationParser().parse()));
			list.add(new CSTNode("OptionalGlobalImports", new OptionalGlobalImportsParser().parse()));
			list.add(new CSTNode("OptionalLocalStorageDeclarations", new OptionalLocalStorageDeclarationsParser().parse()));
			list.add(new CSTNode(consume(Terminals.DO)));
			list.add(new CSTNode("BlockCmd", new BlockCmdParser().parse()));
			list.add(new CSTNode(consume(Terminals.ENDFUN)));
		} 
		else {
			System.out.println(tokenlist.toString());
			throw new GrammarError("GrammarError at: "+ this.getClass().toString() + " terminal found: " + terminal, 0);
		}
		return list;
	}

}
