package conSyn;

import absSyn.IAbsSyn;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

/**
 * Created by tobi on 17.12.16.
 */
public class IdentsConcSyn extends AbstractConcSyn implements IConcSyn {
    public IdentsConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public IAbsSyn toAbsSyn() {
        //TODO: implement
        return null;
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.SKIP) {
            consume();
            parseNext(new RepeatingOptionalIdentsConcSyn(getTokenList(), getCounter()));
        } else {
            throwGrammarError();
        }
    }
}
