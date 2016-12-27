package conSyn;

import absSyn.ComplImagAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.token.IToken;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class ComplImagConcSyn extends AbstractConcSyn implements IConcSyn {
    private ExpressionConcSyn expressionConcSyn;

    public ComplImagConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    private IToken token;
    @Override
    public ComplImagAbsSyn toAbsSyn() throws ContextError {
        return new ComplImagAbsSyn(expressionConcSyn.toAbsSyn());
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.IMAG) {
            consume();
            if (getTokenList().getCurrent().getTerminal() == Terminal.LPAREN) {
                consume();
                expressionConcSyn = new ExpressionConcSyn(this.getTokenList(), getCounter());
                this.parseNext(expressionConcSyn);
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
