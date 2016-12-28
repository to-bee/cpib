package absSyn;

import scanner.errors.ContextError;

/**
 * Created by ylaub on 28.12.2016.
 */
public class RepeatingOptionalGlobalImportsAbsSyn1 extends AbstractAbsSyn implements IAbsSyn {
    private final GlobalImportAbsSyn globalImportAbsSyn;
    private final RepeatingOptionalGlobalImportsAbsSyn1 repeatingOptionalGlobalImportsAbsSyn1;

    public RepeatingOptionalGlobalImportsAbsSyn1(GlobalImportAbsSyn globalImportAbsSyn, RepeatingOptionalGlobalImportsAbsSyn1 repeatingOptionalGlobalImportsAbsSyn1) {

        this.globalImportAbsSyn = globalImportAbsSyn;
        this.repeatingOptionalGlobalImportsAbsSyn1 = repeatingOptionalGlobalImportsAbsSyn1;
    }

    @Override
    public void check() throws ContextError {
        globalImportAbsSyn.check();
        repeatingOptionalGlobalImportsAbsSyn1.check();
    }
}
