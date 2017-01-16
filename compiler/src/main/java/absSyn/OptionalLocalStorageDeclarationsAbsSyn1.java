package absSyn;

import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

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


    public String toString(int counter) {
        return "OptionalLocalStorageDeclarationsAbsSyn1:\r\n\t" + storageDeclarationAbsSyn.toString(counter) +
                "," + repeatingOptionalStorageDeclarationsAbsSyn.toString(counter);
    }

    @Override
    public void check() throws ContextError {
        storageDeclarationAbsSyn.check();
        repeatingOptionalStorageDeclarationsAbsSyn.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
