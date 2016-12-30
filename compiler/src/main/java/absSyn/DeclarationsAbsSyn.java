package absSyn;

import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 26.12.2016.
 */
public class DeclarationsAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final DeclarationAbsSyn declarationAbsSyn;
    private final RepeatingOptionalDeclarationsAbsSyn repeatingOptionalDeclarationsAbsSyn;

    public DeclarationsAbsSyn(DeclarationAbsSyn declarationAbsSyn, RepeatingOptionalDeclarationsAbsSyn repeatingOptionalDeclarationsAbsSyn) {
        this.declarationAbsSyn = declarationAbsSyn;
        this.repeatingOptionalDeclarationsAbsSyn = repeatingOptionalDeclarationsAbsSyn;
    }

    @Override
    public void check() throws ContextError {
        declarationAbsSyn.check();
        repeatingOptionalDeclarationsAbsSyn.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
