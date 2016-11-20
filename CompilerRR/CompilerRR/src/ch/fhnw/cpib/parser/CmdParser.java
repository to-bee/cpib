package ch.fhnw.cpib.parser;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.cst.CSTNode;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class CmdParser extends AbstractParser {

	public CmdParser() {
		super();
	}

	@Override
	public List<CSTNode> parse() throws GrammarError {
		List<CSTNode> list = new LinkedList<CSTNode>();
		if (terminal == Terminals.SKIP) {
			list.add(new CSTNode(consume(Terminals.SKIP)));
		} else if (terminal == Terminals.LPAREN) {
			list.add(new CSTNode("Expression", new ExpressionParser().parse()));
			list.add(new CSTNode(consume(Terminals.BECOMES)));
			list.add(new CSTNode("Expression", new ExpressionParser().parse()));
		} else if (terminal == Terminals.ADDOPR) {
			list.add(new CSTNode("Expression", new ExpressionParser().parse()));
			list.add(new CSTNode(consume(Terminals.BECOMES)));
			list.add(new CSTNode("Expression", new ExpressionParser().parse()));
		} else if (terminal == Terminals.NOTOPER) {
			list.add(new CSTNode("Expression", new ExpressionParser().parse()));
			list.add(new CSTNode(consume(Terminals.BECOMES)));
			list.add(new CSTNode("Expression", new ExpressionParser().parse()));
		} else if (terminal == Terminals.IDENT) {
			list.add(new CSTNode("Expression", new ExpressionParser().parse()));
			list.add(new CSTNode(consume(Terminals.BECOMES)));
			list.add(new CSTNode("Expression", new ExpressionParser().parse()));
		} else if (terminal == Terminals.LITERAL) {
			list.add(new CSTNode("Expression", new ExpressionParser().parse()));
			list.add(new CSTNode(consume(Terminals.BECOMES)));
			list.add(new CSTNode("Expression", new ExpressionParser().parse()));
		} else if (terminal == Terminals.IF) {
			list.add(new CSTNode(consume(Terminals.IF)));
			list.add(new CSTNode("Expression", new ExpressionParser().parse()));
			list.add(new CSTNode(consume(Terminals.THEN)));
			list.add(new CSTNode("BlockCmd", new BlockCmdParser().parse()));
			list.add(new CSTNode(consume(Terminals.ELSE)));
			list.add(new CSTNode("BlockCmd", new BlockCmdParser().parse()));
			list.add(new CSTNode(consume(Terminals.ENDIF)));
		} else if (terminal == Terminals.WHILE) {
			list.add(new CSTNode(consume(Terminals.WHILE)));
			list.add(new CSTNode("Expression", new ExpressionParser().parse()));
			list.add(new CSTNode(consume(Terminals.DO)));
			list.add(new CSTNode("BlockCmd", new BlockCmdParser().parse()));
			list.add(new CSTNode(consume(Terminals.ENDWHILE)));
		} else if (terminal == Terminals.CALL) {
			list.add(new CSTNode(consume(Terminals.CALL)));
			list.add(new CSTNode(consume(Terminals.IDENT)));
			list.add(new CSTNode("Expression", new ExpressionParser().parse()));
			list.add(new CSTNode("OptionalGlobalInits", new OptionalGlobalInitsParser().parse()));
		} else if (terminal == Terminals.DEBUGIN) {
			list.add(new CSTNode(consume(Terminals.DEBUGIN)));
			list.add(new CSTNode("Expression", new ExpressionParser().parse()));
		} else if (terminal == Terminals.DEBUGOUT) {
			list.add(new CSTNode(consume(Terminals.DEBUGOUT)));
			list.add(new CSTNode("Expression", new ExpressionParser().parse()));
		} else if (terminal == Terminals.SWITCH) {
			list.add(new CSTNode(consume(Terminals.SWITCH)));
			list.add(new CSTNode("Expression", new ExpressionParser().parse()));
			list.add(new CSTNode(consume(Terminals.CASE)));
			list.add(new CSTNode(consume(Terminals.LITERAL)));
			list.add(new CSTNode(consume(Terminals.COLON)));
			list.add(new CSTNode("BlockCmd", new BlockCmdParser().parse()));
			list.add(new CSTNode("RepeatingOptionalCase", new RepeatingOptionalCaseParser().parse()));
			list.add(new CSTNode(consume(Terminals.CASEDEFAULT)));
			list.add(new CSTNode(consume(Terminals.COLON)));
			list.add(new CSTNode("BlockCmd", new BlockCmdParser().parse()));
			list.add(new CSTNode(consume(Terminals.ENDSWITCH)));
			
		} else {
			System.out.println(tokenlist.toString());
			throw new GrammarError("GrammarError at: "+ this.getClass().toString() + " terminal found: " + terminal, 0);
		}
		return list;
	}

}
