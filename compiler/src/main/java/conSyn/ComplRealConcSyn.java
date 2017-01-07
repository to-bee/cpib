package conSyn;

import absSyn.ComplRealAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.token.IToken;
import scanner.tokenList.ITokenList;
/**
 * Created by tobi on 17.12.16.
 */
public class ComplRealConcSyn extends AbstractConcSyn implements IConcSyn {
    private ExpressionConcSyn expressionConcSyn;
    private IToken token;

    public ComplRealConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public ComplRealAbsSyn toAbsSyn() throws ContextError {
        return new ComplRealAbsSyn(token, expressionConcSyn.toAbsSyn());
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.REAL) {
            this.token = this.getTokenList().getCurrent();
            consume();
            if (getTokenList().getCurrent().getTerminal() == Terminal.LPAREN) {
                consume();
                expressionConcSyn = new ExpressionConcSyn(getTokenList(), getCounter());
                parseNext(expressionConcSyn);
                if (getTokenList().getCurrent().getTerminal() == Terminal.RPAREN) {
                    consume();
                } else {
                    throwGrammarError();
                }
            } else {
                throwGrammarError();
            }
        } else {
            throwGrammarError();
        }
    }
}
