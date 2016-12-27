package absSyn;

/**
 * Created by ylaub on 26.12.2016.
 */
public class RepeatingOptionalStorageDeclarationsAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final StorageDeclarationAbsSyn storageDeclarationAbsSyn;
    private final RepeatingOptionalStorageDeclarationsAbsSyn repeatingOptionalStorageDeclarationsAbsSyn;

    public RepeatingOptionalStorageDeclarationsAbsSyn(StorageDeclarationAbsSyn storageDeclarationAbsSyn, RepeatingOptionalStorageDeclarationsAbsSyn repeatingOptionalStorageDeclarationsAbsSyn) {

        this.storageDeclarationAbsSyn = storageDeclarationAbsSyn;
        this.repeatingOptionalStorageDeclarationsAbsSyn = repeatingOptionalStorageDeclarationsAbsSyn;
    }

    @Override
    public void check() {
        this.storageDeclarationAbsSyn.check();
        this.repeatingOptionalStorageDeclarationsAbsSyn.check();
    }
}
