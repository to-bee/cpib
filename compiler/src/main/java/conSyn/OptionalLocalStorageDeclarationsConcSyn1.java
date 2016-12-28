package conSyn;

import absSyn.OptionalLocalStorageDeclarationsAbsSyn1;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class OptionalLocalStorageDeclarationsConcSyn1 extends AbstractConcSyn implements IConcSyn {
    private StorageDeclarationConcSyn storageDeclarationConcSyn;
    private RepeatingOptionalStorageDeclarationsConcSyn repeatingOptionalStorageDeclarationsConcSyn;

    public OptionalLocalStorageDeclarationsConcSyn1(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public OptionalLocalStorageDeclarationsAbsSyn1 toAbsSyn() throws ContextError {
        return new OptionalLocalStorageDeclarationsAbsSyn1(storageDeclarationConcSyn.toAbsSyn(), repeatingOptionalStorageDeclarationsConcSyn.toAbsSyn());
    }

    /**
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        consume();
        storageDeclarationConcSyn = new StorageDeclarationConcSyn(getTokenList(), getCounter());
        parseNext(storageDeclarationConcSyn);
        repeatingOptionalStorageDeclarationsConcSyn = new RepeatingOptionalStorageDeclarationsConcSyn(getTokenList(), getCounter());
        parseNext(repeatingOptionalStorageDeclarationsConcSyn);
    }
}
