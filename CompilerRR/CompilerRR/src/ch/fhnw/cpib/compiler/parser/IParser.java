package ch.fhnw.cpib.compiler.parser;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.error.GrammarError;

public interface IParser {

	IConcSyn.IProgram parse() throws GrammarError;
	
}
