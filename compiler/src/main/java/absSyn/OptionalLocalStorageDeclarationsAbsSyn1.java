package absSyn;

import scanner.errors.ContextError;

/**
 * Created by ylaub on 28.12.2016.
 */
public class OptionalLocalStorageDeclarationsAbsSyn1 extends AbstractAbsSyn implements IAbsSyn {

    private final StorageDeclarationAbsSyn storageDeclarationAbsSyn;
    private final RepeatingOptionalStorageDeclarationsAbsSyn repeatingOptionalStorageDeclarationsAbsSyn;

    public OptionalLocalStorageDeclarationsAbsSyn1(StorageDeclarationAbsSyn storageDeclarationAbsSyn, RepeatingOptionalStorageDeclarationsAbsSyn repeatingOptionalStorageDeclarationsAbsSyn) {

        this.storageDeclarationAbsSyn = storageDeclarationAbsSyn;
        this.repeatingOptionalStorageDeclarationsAbsSyn = repeatingOptionalStorageDeclarationsAbsSyn;
    }

    @Override
    public void check() throws ContextError {
        storageDeclarationAbsSyn.check();
        repeatingOptionalStorageDeclarationsAbsSyn.check();
    }
}
