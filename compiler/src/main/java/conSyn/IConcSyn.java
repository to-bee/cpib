package conSyn;

import absSyn.IAbsSyn;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;

public interface IConcSyn {
    void parse() throws GrammarError;
    IAbsSyn toAbsSyn() throws ContextError;
}
