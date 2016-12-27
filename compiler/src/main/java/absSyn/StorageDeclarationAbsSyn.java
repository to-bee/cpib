package absSyn;

/**
 * Created by ylaub on 26.12.2016.
 */
public class StorageDeclarationAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final TypedIdentAbsSyn typedIdentAbsSyn;
    private final OptionalChangeModeAbsSyn optionalChangeModeAbsSyn;

    public StorageDeclarationAbsSyn(TypedIdentAbsSyn typedIdentAbsSyn, OptionalChangeModeAbsSyn optionalChangeModeAbsSyn) {

        this.typedIdentAbsSyn = typedIdentAbsSyn;
        this.optionalChangeModeAbsSyn = optionalChangeModeAbsSyn;
    }

    @Override
    public void check() {
        this.typedIdentAbsSyn.check();
        this.optionalChangeModeAbsSyn.check();
    }
}

