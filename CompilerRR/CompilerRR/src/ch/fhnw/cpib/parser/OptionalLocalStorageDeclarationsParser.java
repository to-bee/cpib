package ch.fhnw.cpib.parser;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.cst.CSTNode;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class OptionalLocalStorageDeclarationsParser extends AbstractParser {

	public OptionalLocalStorageDeclarationsParser() {
		super();
	}
	
	public List<CSTNode> parse() throws GrammarError {
		List<CSTNode> list = new LinkedList<CSTNode>();
		if (terminal == Terminals.DO) {
			//TODO: stimmt es, dass dies einfach leer ist?
		} 
		else if(terminal == Terminals.LOCAL){
			list.add(new CSTNode(consume(Terminals.LOCAL)));
			list.add(new CSTNode("StorageDeclaration", new StorageDeclarationParser().parse()));
			list.add(new CSTNode("RepeatingOptionalStorageDeclarations", new RepeatingOptionalStorageDeclarationsParser().parse()));
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
		return list;
	}
	
}
