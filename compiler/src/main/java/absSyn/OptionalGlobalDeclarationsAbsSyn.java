package absSyn;

import scanner.errors.ContextError;

/**
 * Created by ylaub on 26.12.2016.
 */
public class OptionalGlobalDeclarationsAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private DeclarationsAbsSyn declarationsAbsSyn;

    public OptionalGlobalDeclarationsAbsSyn(DeclarationsAbsSyn declarationsAbsSyn) {
        this.declarationsAbsSyn = declarationsAbsSyn;
    }

    @Override
    public void check() throws ContextError {
        declarationsAbsSyn.check();
    }
}
