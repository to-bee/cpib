package conSyn;

import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

/**
 * Created by tobi on 17.12.16.
 */
public class Idents extends AbstractConcSyn implements IConcSyn {
    public Idents(ITokenList tokenList) {
        super(tokenList);
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.SKIP) {
            consume();
            parseNext(new RepeatingOptionalIdents(getTokenList()));
        } else {
            throwGrammarError();
        }
    }
}
