package absSyn;

import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 26.12.2016.
 */
public class OptionalGlobalImportsAbsSyn1 extends AbstractAbsSyn implements IAbsSyn {


    private final GlobalImportAbsSyn globalImportAbsSyn;
    private final RepeatingOptionalGlobalImportsAbsSyn repeatingOptionalGlobalImportsAbsSyn;

    public OptionalGlobalImportsAbsSyn1(GlobalImportAbsSyn globalImportAbsSyn, RepeatingOptionalGlobalImportsAbsSyn repeatingOptionalGlobalImportsAbsSyn) {

        this.globalImportAbsSyn = globalImportAbsSyn;
        this.repeatingOptionalGlobalImportsAbsSyn = repeatingOptionalGlobalImportsAbsSyn;
    }

    @Override
    public void check() throws ContextError {
        globalImportAbsSyn.check();
        repeatingOptionalGlobalImportsAbsSyn.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
