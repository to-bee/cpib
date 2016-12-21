package conSyn;

import absSyn.IAbsSyn;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

/**
 * Created by tobi on 17.12.16.
 */
public class RepeatingOptionalStorageDeclarationsConcSyn extends AbstractConcSyn implements IConcSyn {
    public RepeatingOptionalStorageDeclarationsConcSyn(ITokenList tokenList, int i) {
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

        } else if (getTokenList().getCurrent().getTerminal() == Terminal.SEMICOLON) {
            consume();
            parseNext(new StorageDeclarationConcSyn(getTokenList(), getCounter()));
            parseNext(new RepeatingOptionalStorageDeclarationsConcSyn(getTokenList(), getCounter()));
        } else {
            throwGrammarError();
        }
    }
}
