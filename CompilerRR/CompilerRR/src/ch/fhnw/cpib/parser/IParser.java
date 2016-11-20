package ch.fhnw.cpib.parser;

import java.util.List;

import ch.fhnw.cpib.compiler.cst.CSTNode;
import ch.fhnw.cpib.compiler.error.GrammarError;

public interface IParser {

	List<CSTNode> parse() throws GrammarError;
	
}
