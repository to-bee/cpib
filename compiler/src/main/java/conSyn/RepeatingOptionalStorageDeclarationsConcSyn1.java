package conSyn;

import absSyn.RepeatingOptionalStorageDeclarationsAbsSyn1;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class RepeatingOptionalStorageDeclarationsConcSyn1 extends AbstractConcSyn implements IConcSyn {
    private StorageDeclarationConcSyn storageDeclarationConcSyn;
    private RepeatingOptionalStorageDeclarationsConcSyn repeatingOptionalStorageDeclarationsConcSyn;

    public RepeatingOptionalStorageDeclarationsConcSyn1(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public RepeatingOptionalStorageDeclarationsAbsSyn1 toAbsSyn() throws ContextError {
        return new RepeatingOptionalStorageDeclarationsAbsSyn1(storageDeclarationConcSyn.toAbsSyn(), repeatingOptionalStorageDeclarationsConcSyn.toAbsSyn());
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
