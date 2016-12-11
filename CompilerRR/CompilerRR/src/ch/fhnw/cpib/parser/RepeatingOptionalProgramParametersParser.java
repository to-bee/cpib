package ch.fhnw.cpib.parser;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.cst.CSTNode;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class RepeatingOptionalProgramParametersParser extends AbstractParser {

	public RepeatingOptionalProgramParametersParser() {
		super();
	}
	
	public List<CSTNode> parse() throws GrammarError {
		List<CSTNode> list = new LinkedList<CSTNode>();
		if (terminal == Terminals.RPAREN) {
			//TODO: stimmt es, dass dies einfach leer ist?
		} 
		else if(terminal == Terminals.COMMA){
			list.add(new CSTNode(consume(Terminals.COMMA)));
			list.add(new CSTNode("OptionalFlowMode", new OptionalFlowModeParser().parse()));
			list.add(new CSTNode("OptionalChangemode", new OptionalChangemodeParser().parse()));
			list.add(new CSTNode("TypeIdent", new TypeIdentParser().parse()));
			list.add(new CSTNode("RepeatingOptionalProgramParameters", new RepeatingOptionalProgramParametersParser().parse()));
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
		return list;
	}
	
}