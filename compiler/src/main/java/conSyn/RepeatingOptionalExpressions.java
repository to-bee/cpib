package conSyn;

import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

/**
 * Created by tobi on 17.12.16.
 */
public class RepeatingOptionalExpressions extends AbstractConcSyn implements IConcSyn {
    public RepeatingOptionalExpressions(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.RPAREN) {
            consume();
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.COMMA) {
            consume();
            parseNext(new Expression(getTokenList(), getCounter()));
            parseNext(new RepeatingOptionalExpressions(getTokenList(), getCounter()));
        } else {
            throwGrammarError();
        }
    }
}
