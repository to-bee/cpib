package parser;

import conSyn.IConcSyn;
import scanner.errors.GrammarError;

/**
 * Created by tobi on 18.10.16.
 */
public interface IParser {
    IConcSyn parseProgram() throws GrammarError;
}