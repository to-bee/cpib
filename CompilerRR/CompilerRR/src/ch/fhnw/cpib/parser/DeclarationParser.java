package ch.fhnw.cpib.parser;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.cst.CSTNode;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class DeclarationParser extends AbstractParser {

	public DeclarationParser() {
		super();
	}

	@Override
	public List<CSTNode> parse() throws GrammarError {
		List<CSTNode> list = new LinkedList<CSTNode>();
		if (terminal == Terminals.IDENT) {
			list.add(new CSTNode("StorageDeclaration", new StorageDeclarationParser().parse()));
		} 
		else if (terminal == Terminals.CHANGEMODE) {
			list.add(new CSTNode("StorageDeclaration", new StorageDeclarationParser().parse()));
		}
		else if (terminal == Terminals.FUN) {
			list.add(new CSTNode("FunctionDeclaration", new FunctionDeclarationParser().parse()));
		} 
		else if (terminal == Terminals.PROC) {
			list.add(new CSTNode("ProcedureDeclaration", new ProcedureDeclarationParser().parse()));
		} 
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString() + " terminal found: " + terminal, 0);
		}
		return list;
		
	}

}
