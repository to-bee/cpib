package conSyn;

import absSyn.FactorExpressionAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.token.IToken;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class FactorExpressionConcSyn extends AbstractConcSyn implements IConcSyn {
    private ExpressionConcSyn expressionConcSyn;
    private RepeatingOptionalExpressionsConcSyn repeatingOptionalExpressionsConcSyn;

    public ExpressionConcSyn getExpressionConcSyn() {
        return expressionConcSyn;
    }

    public RepeatingOptionalExpressionsConcSyn getRepeatingOptionalExpressionsConcSyn() {
        return repeatingOptionalExpressionsConcSyn;
    }

    public FactorExpressionConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public FactorExpressionAbsSyn toAbsSyn() throws ContextError {
        return new FactorExpressionAbsSyn(expressionConcSyn.toAbsSyn(), repeatingOptionalExpressionsConcSyn.toAbsSyn());
    }

    @Override
    public void parse() throws GrammarError {
        consume();

        expressionConcSyn = new ExpressionConcSyn(getTokenList(), getCounter());
        parseNext(expressionConcSyn);

        repeatingOptionalExpressionsConcSyn = new RepeatingOptionalExpressionsConcSyn(getTokenList(), getCounter());
        parseNext(repeatingOptionalExpressionsConcSyn);

        if (getTokenList().getCurrent().getTerminal() == Terminal.RPAREN) {
            consume();
        } else {
            throwGrammarError();
        }
    }
}
