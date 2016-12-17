package conSyn;

import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

/**
 * Created by tobi on 17.12.16.
 */
public class RepeatingOptionalParameters extends AbstractConcSyn implements IConcSyn {
    public RepeatingOptionalParameters(ITokenList tokenList) {
        super(tokenList);
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.RPAREN) {

        } else if (getTokenList().getCurrent().getTerminal() == Terminal.COMMA) {
            consume();
            parseNext(new Parameter(getTokenList()));
            parseNext(new RepeatingOptionalParameters(getTokenList()));
        } else {
            throwGrammarError();
        }
    }
}
