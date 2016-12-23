package conSyn;

import absSyn.IAbsSyn;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

/**
 * Created by tobi on 17.12.16.
 */
public class RepeatingOptionalExpressionsConcSyn extends AbstractConcSyn implements IConcSyn {
    public RepeatingOptionalExpressionsConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public IAbsSyn toAbsSyn() {
        //TODO: implement
        return null;
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.RPAREN) {

        } else if (getTokenList().getCurrent().getTerminal() == Terminal.COMMA) {
            consume();
            parseNext(new ExpressionConcSyn(getTokenList(), getCounter()));
            parseNext(new RepeatingOptionalExpressionsConcSyn(getTokenList(), getCounter()));
        } else {
            throwGrammarError();
        }
    }
}
