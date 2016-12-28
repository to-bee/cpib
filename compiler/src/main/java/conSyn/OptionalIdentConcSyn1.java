package conSyn;

import absSyn.OptionalIdentAbsSyn1;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class OptionalIdentConcSyn1 extends AbstractConcSyn implements IConcSyn {
    private ExpressionListConcSyn expressionListConcSyn;

    public OptionalIdentConcSyn1(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public OptionalIdentAbsSyn1 toAbsSyn() throws ContextError {
        return new OptionalIdentAbsSyn1(expressionListConcSyn.toAbsSyn());
    }

    /**
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        expressionListConcSyn = new ExpressionListConcSyn(getTokenList(), getCounter());
        parseNext(expressionListConcSyn);

    }
}
