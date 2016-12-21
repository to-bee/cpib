package conSyn;

import absSyn.IAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class ComplRealConcSyn extends AbstractConcSyn implements IConcSyn {
    public ComplRealConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public IAbsSyn toAbsSyn() {
        //TODO: implement
        return null;
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.REAL) {
            consume();
            if (getTokenList().getCurrent().getTerminal() == Terminal.LPAREN) {
                consume();
                parseNext(new ExpressionConcSyn(getTokenList(), getCounter()));
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
