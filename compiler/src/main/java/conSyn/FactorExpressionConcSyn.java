package conSyn;

import absSyn.CmdSkipAbsSyn;
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

    public IToken getToken() {
        return token;
    }

    private IToken token;

    public FactorExpressionConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public FactorExpressionAbsSyn toAbsSyn() throws ContextError {
        return new FactorExpressionAbsSyn(token, expressionConcSyn.toAbsSyn(), repeatingOptionalExpressionsConcSyn.toAbsSyn());
    }

    @Override
    public void parse() throws GrammarError {
        this.token = this.getTokenList().getCurrent();
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
