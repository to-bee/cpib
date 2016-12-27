package absSyn;

import scanner.errors.ContextError;

/**
 * Created by ylaub on 26.12.2016.
 */
public class RepeatingOptionalGlobalImportsAbsSyn extends AbstractAbsSyn implements IAbsSyn {
    private final GlobalImportAbsSyn globalImportAbsSyn;
    private final RepeatingOptionalGlobalImportsAbsSyn repeatingOptionalGlobalImportsAbsSyn;

    public RepeatingOptionalGlobalImportsAbsSyn(GlobalImportAbsSyn globalImportAbsSyn, RepeatingOptionalGlobalImportsAbsSyn repeatingOptionalGlobalImportsAbsSyn) {

        this.globalImportAbsSyn = globalImportAbsSyn;
        this.repeatingOptionalGlobalImportsAbsSyn = repeatingOptionalGlobalImportsAbsSyn;
    }

    @Override
    public void check() throws ContextError {
        this.globalImportAbsSyn.check();
        this.repeatingOptionalGlobalImportsAbsSyn.check();
    }
}
