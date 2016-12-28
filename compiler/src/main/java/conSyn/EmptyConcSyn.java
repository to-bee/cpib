package conSyn;

import absSyn.EmptyAbsSyn;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by ylaub on 28.12.2016.
 */
public class EmptyConcSyn extends AbstractConcSyn implements IConcSyn {
    public EmptyConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public void parse() throws GrammarError {

    }

    @Override
    public EmptyAbsSyn toAbsSyn() throws ContextError {
        return new EmptyAbsSyn();
    }
}
