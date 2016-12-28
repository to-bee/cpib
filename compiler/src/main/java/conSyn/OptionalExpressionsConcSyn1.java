package conSyn;

import absSyn.OptionalExpressionsAbsSyn1;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class OptionalExpressionsConcSyn1 extends AbstractConcSyn implements IConcSyn {
    private RepeatingOptionalExpressionsConcSyn repeatingOptionalExpressionsConcSyn;
    private ExpressionConcSyn expressionConcSyn;

    public OptionalExpressionsConcSyn1(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public OptionalExpressionsAbsSyn1 toAbsSyn() throws ContextError {
        return new OptionalExpressionsAbsSyn1(expressionConcSyn.toAbsSyn(), repeatingOptionalExpressionsConcSyn.toAbsSyn());
    }

    /**
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        expressionConcSyn = new ExpressionConcSyn(getTokenList(), getCounter());
        parseNext(expressionConcSyn);

        repeatingOptionalExpressionsConcSyn = new RepeatingOptionalExpressionsConcSyn(getTokenList(), getCounter());
        parseNext(repeatingOptionalExpressionsConcSyn);
    }
}
