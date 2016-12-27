package conSyn;

import absSyn.RepeatingOptionalExpressionsAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
/**
 * Created by tobi on 17.12.16.
 */
public class RepeatingOptionalExpressionsConcSyn extends AbstractConcSyn implements IConcSyn {
    private ExpressionConcSyn expressionConcSyn;
    private RepeatingOptionalExpressionsConcSyn repeatingOptionalExpressionsConcSyn;

    public RepeatingOptionalExpressionsConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public RepeatingOptionalExpressionsAbsSyn toAbsSyn() throws ContextError {
        return new RepeatingOptionalExpressionsAbsSyn(expressionConcSyn.toAbsSyn(), repeatingOptionalExpressionsConcSyn.toAbsSyn());
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.RPAREN) {

        } else if (getTokenList().getCurrent().getTerminal() == Terminal.COMMA) {
            consume();

            expressionConcSyn = new ExpressionConcSyn(getTokenList(), getCounter());
            parseNext(expressionConcSyn);

            repeatingOptionalExpressionsConcSyn = new RepeatingOptionalExpressionsConcSyn(getTokenList(), getCounter());
            parseNext(repeatingOptionalExpressionsConcSyn);
        } else {
            throwGrammarError();
        }
    }
}
