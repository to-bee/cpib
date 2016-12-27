package conSyn;

import absSyn.RepeatingOptionalStorageDeclarationsAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
/**
 * Created by tobi on 17.12.16.
 */
public class RepeatingOptionalStorageDeclarationsConcSyn extends AbstractConcSyn implements IConcSyn {
    private StorageDeclarationConcSyn storageDeclarationConcSyn;
    private RepeatingOptionalStorageDeclarationsConcSyn repeatingOptionalStorageDeclarationsConcSyn;

    public RepeatingOptionalStorageDeclarationsConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public RepeatingOptionalStorageDeclarationsAbsSyn toAbsSyn()throws ContextError {
        return new RepeatingOptionalStorageDeclarationsAbsSyn(storageDeclarationConcSyn.toAbsSyn(), repeatingOptionalStorageDeclarationsConcSyn.toAbsSyn());
    }

    /**
     * TODO
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.DO) {

        } else if (getTokenList().getCurrent().getTerminal() == Terminal.SEMICOLON) {
            consume();

            storageDeclarationConcSyn = new StorageDeclarationConcSyn(getTokenList(), getCounter());
            parseNext(storageDeclarationConcSyn);

            repeatingOptionalStorageDeclarationsConcSyn = new RepeatingOptionalStorageDeclarationsConcSyn(getTokenList(), getCounter());
            parseNext(repeatingOptionalStorageDeclarationsConcSyn);
        } else {
            throwGrammarError();
        }
    }
}
