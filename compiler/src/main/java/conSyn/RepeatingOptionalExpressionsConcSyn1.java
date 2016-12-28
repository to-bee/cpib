package conSyn;

import absSyn.RepeatingOptionalExpressionsAbsSyn;
import absSyn.RepeatingOptionalExpressionsAbsSyn1;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class RepeatingOptionalExpressionsConcSyn1 extends AbstractConcSyn implements IConcSyn {
    private ExpressionConcSyn expressionConcSyn;
    private RepeatingOptionalExpressionsConcSyn repeatingOptionalExpressionsConcSyn;

    public RepeatingOptionalExpressionsConcSyn1(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public RepeatingOptionalExpressionsAbsSyn1 toAbsSyn() throws ContextError {
        return new RepeatingOptionalExpressionsAbsSyn1(expressionConcSyn.toAbsSyn(), repeatingOptionalExpressionsConcSyn.toAbsSyn());
    }

    /**
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
            consume();
            expressionConcSyn = new ExpressionConcSyn(getTokenList(), getCounter());
            parseNext(expressionConcSyn);
            repeatingOptionalExpressionsConcSyn = new RepeatingOptionalExpressionsConcSyn(getTokenList(), getCounter());
            parseNext(repeatingOptionalExpressionsConcSyn);
    }
}
