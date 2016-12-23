package conSyn;

import absSyn.IAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class OptionalGlobalImportsConcSyn extends AbstractConcSyn implements IConcSyn {
    public OptionalGlobalImportsConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public IAbsSyn toAbsSyn() {
        //TODO: implement
        return null;
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.DO
                || getTokenList().getCurrent().getTerminal() == Terminal.LOCAL) {

        } else if (getTokenList().getCurrent().getTerminal() == Terminal.GLOBAL) {
            consume();
            parseNext(new GlobalImportConcSyn(getTokenList(), getCounter()));
            parseNext(new RepeatingOptionalGlobalImportsConcSyn(getTokenList(), getCounter()));
        } else {
            throwGrammarError();
        }
    }
}