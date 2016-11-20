package ch.fhnw.cpib.parser;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.cst.CSTNode;
import ch.fhnw.cpib.compiler.error.GrammarError;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class OptionalParametersParser extends AbstractParser {

	public OptionalParametersParser() {
		super();
	}

	@Override
	public List<CSTNode> parse() throws GrammarError {
		List<CSTNode> list = new LinkedList<CSTNode>();
		if (terminal == Terminals.RPAREN) {
			// TODO: leer?
		}
		else if (terminal == Terminals.IDENT) {
			list.add(new CSTNode("Parameter", new ParameterParser().parse()));
			list.add(new CSTNode("RepeatingOptionalParameters", new RepeatingOptionalParametersParser().parse()));
		} 
		else if (terminal == Terminals.CHANGEMODE) {
			list.add(new CSTNode("Parameter", new ParameterParser().parse()));
			list.add(new CSTNode("RepeatingOptionalParameters", new RepeatingOptionalParametersParser().parse()));
		}
		else if (terminal == Terminals.MECHMODE) {
			list.add(new CSTNode("Parameter", new ParameterParser().parse()));
			list.add(new CSTNode("RepeatingOptionalParameters", new RepeatingOptionalParametersParser().parse()));
		}
		else if (terminal == Terminals.FLOWMODE) {
			list.add(new CSTNode("Parameter", new ParameterParser().parse()));
			list.add(new CSTNode("RepeatingOptionalParameters", new RepeatingOptionalParametersParser().parse()));
		}
		else {
			throw new GrammarError("GrammarError at: "+ this.getClass().toString(), 0);
		}
		return list;
	}

}
