package absSyn;

import scanner.errors.ContextError;

/**
 * Created by ylaub on 28.12.2016.
 */
public class OptionalGlobalDeclarationsAbsSyn1 extends AbstractAbsSyn implements IAbsSyn {

    private DeclarationsAbsSyn declarationsAbsSyn;

    public OptionalGlobalDeclarationsAbsSyn1(DeclarationsAbsSyn declarationsAbsSyn) {

        this.declarationsAbsSyn = declarationsAbsSyn;
    }

    @Override
    public void check() throws ContextError {
        declarationsAbsSyn.check();
    }
}
