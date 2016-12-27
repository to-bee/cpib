package absSyn;
/**
 * Created by ylaub on 26.12.2016.
 */
public class OptionalLocalStorageDeclarationsAbsSyn extends AbstractAbsSyn implements IAbsSyn{

    private final StorageDeclarationAbsSyn storageDeclarationAbsSyn;
    private final RepeatingOptionalStorageDeclarationsAbsSyn repeatingOptionalStorageDeclarationsAbsSyn;

    public OptionalLocalStorageDeclarationsAbsSyn(StorageDeclarationAbsSyn storageDeclarationAbsSyn, RepeatingOptionalStorageDeclarationsAbsSyn repeatingOptionalStorageDeclarationsAbsSyn) {

        this.storageDeclarationAbsSyn = storageDeclarationAbsSyn;
        this.repeatingOptionalStorageDeclarationsAbsSyn = repeatingOptionalStorageDeclarationsAbsSyn;
    }

    @Override
    public void check() {
        storageDeclarationAbsSyn.check();
        repeatingOptionalStorageDeclarationsAbsSyn.check();
    }
}
