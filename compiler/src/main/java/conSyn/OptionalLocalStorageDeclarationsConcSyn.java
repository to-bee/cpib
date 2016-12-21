package conSyn;

import absSyn.IAbsSyn;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

/**
 * Created by tobi on 17.12.16.
 */
public class OptionalLocalStorageDeclarationsConcSyn extends AbstractConcSyn implements IConcSyn {
    public OptionalLocalStorageDeclarationsConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public IAbsSyn toAbsSyn() {
        //TODO: implement
        return null;
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.DO) {

        } else if (getTokenList().getCurrent().getTerminal() == Terminal.LOCAL) {
            consume();
            parseNext(new StorageDeclarationConcSyn(getTokenList(), getCounter()));
            parseNext(new RepeatingOptionalStorageDeclarationsConcSyn(getTokenList(), getCounter()));
        }
    }
}
