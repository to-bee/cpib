package parser;

import absSyn.ProgramParameterListAbsSyn;
import conSyn.AbstractConcSyn;
import conSyn.IConcSyn;
import conSyn.ProgramConcSyn;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 27/09/16.
 */
public class Parser extends AbstractConcSyn implements IParser, IConcSyn {


    private final ITokenList tokenList;
    private ProgramConcSyn programConSyn;

    public Parser(ITokenList tokenList) {
        super(tokenList, -1);
        this.tokenList = tokenList.clone();
    }

    public void parse() throws GrammarError {
        programConSyn = new ProgramConcSyn(tokenList, this.getCounter());
        this.parseNext(programConSyn);
    }

    @Override
    public IAbsSyn toAbsSyn() throws ContextError {
        return this.programConSyn.toAbsSyn();
    }
}