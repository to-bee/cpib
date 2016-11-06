package parser;

import conSyn.IConcSyn;
import model.errors.GrammarError;

/**
 * Created by tobi on 18.10.16.
 */
public interface IParser {
    IConcSyn parse() throws GrammarError;
}