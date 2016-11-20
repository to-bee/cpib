package ch.fhnw.cpib.parser;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import ch.fhnw.cpib.compiler.cst.CSTNode;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class ProgramParser extends AbstractParser{
	
	
	public ProgramParser(LinkedList<Token> tokenlist) {
		super(tokenlist);
	}
	
	public List<CSTNode> parse() throws GrammarError {
		if (terminal == Terminals.PROGRAM) {
			List<CSTNode> list = new LinkedList<CSTNode>();
			
			list.add(new CSTNode(consume(Terminals.PROGRAM)));
			list.add(new CSTNode(consume(Terminals.IDENT)));
			list.add(new CSTNode("ProgramParameterList", new ProgramParameterListParser().parse());
			list.add(new CSTNode(new OptionalGlobalDeclarationsParser().parse());
			list.add(new CSTNode(consume(Terminals.DO)));
			list.add(new CSTNode(new BlockCmdParser().parse());
			list.add(new CSTNode(consume(Terminals.ENDPROGRAM)));
			
			return list;
		} else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
	}
	
	
	
}
