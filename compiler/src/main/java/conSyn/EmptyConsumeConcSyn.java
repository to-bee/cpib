package conSyn;

import absSyn.EmptyConsumeAbsSyn;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by ylaub on 28.12.2016.
 */
public class EmptyConsumeConcSyn extends AbstractConcSyn implements IConcSyn {
    public EmptyConsumeConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public void parse() throws GrammarError {
        consume();
    }

    @Override
    public EmptyConsumeAbsSyn toAbsSyn() throws ContextError {
        return new EmptyConsumeAbsSyn();
    }
}
