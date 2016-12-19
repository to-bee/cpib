package parser;

import absSyn.IAbsSyn;
import conSyn.AbstractConcSyn;
import conSyn.IConcSyn;
import conSyn.Program;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by tobi on 27/09/16.
 */
public class Parser extends AbstractConcSyn implements IParser, IConcSyn {


    private final ITokenList tokenList;

    public Parser(ITokenList tokenList) {
        super(tokenList, -1);
        this.tokenList = tokenList.clone();
    }

    public void parse() throws GrammarError {
        this.parseNext(new Program(tokenList, this.getCounter()));
    }

    @Override
    public IAbsSyn toAbsSyn() {
        throw new NotImplementedException();
    }
}