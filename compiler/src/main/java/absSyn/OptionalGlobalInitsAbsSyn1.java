package absSyn;

import scanner.errors.ContextError;

/**
 * Created by ylaub on 28.12.2016.
 */
public class OptionalGlobalInitsAbsSyn1 extends AbstractAbsSyn implements IAbsSyn {
    private IdentsAbsSyn identsAbsSyn;

    public OptionalGlobalInitsAbsSyn1(IdentsAbsSyn identsAbsSyn) {

        this.identsAbsSyn = identsAbsSyn;
    }

    @Override
    public void check() throws ContextError {
        identsAbsSyn.check();
    }
}
