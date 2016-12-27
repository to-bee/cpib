package absSyn;
/**
 * Created by ylaub on 26.12.2016.
 */
public class OptionalGlobalImportsAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final GlobalImportAbsSyn globalImportAbsSyn;
    private final RepeatingOptionalGlobalImportsAbsSyn repeatingOptionalGlobalImportsAbsSyn;

    public OptionalGlobalImportsAbsSyn(GlobalImportAbsSyn globalImportAbsSyn, RepeatingOptionalGlobalImportsAbsSyn repeatingOptionalGlobalImportsAbsSyn) {
        this.globalImportAbsSyn = globalImportAbsSyn;
        this.repeatingOptionalGlobalImportsAbsSyn = repeatingOptionalGlobalImportsAbsSyn;
    }

    @Override
    public void check() {
        globalImportAbsSyn.check();
        repeatingOptionalGlobalImportsAbsSyn.check();
    }
}
