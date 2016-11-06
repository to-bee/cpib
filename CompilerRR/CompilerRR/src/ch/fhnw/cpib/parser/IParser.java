package ch.fhnw.cpib.parser;

import ch.fhnw.cpib.compiler.error.GrammarError;

public interface IParser {

	void parse() throws GrammarError;
	
}
