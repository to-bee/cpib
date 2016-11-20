package ch.fhnw.cpib.parser;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.cst.CSTNode;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class ProgramParameterListParser extends AbstractParser {

	public ProgramParameterListParser() {
		super();
	}

	@Override
	public List<CSTNode> parse() throws GrammarError {
		List<CSTNode> list = new LinkedList<CSTNode>();
		if (terminal == Terminals.LPAREN) {
			list.add(new CSTNode(consume(Terminals.LPAREN)));
			list.add(new CSTNode("OptionalProgramParameters", new OptionalProgramParametersParser().parse()));
			list.add(new CSTNode(consume(Terminals.RPAREN)));
		} 
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
		return list;
	}

}
