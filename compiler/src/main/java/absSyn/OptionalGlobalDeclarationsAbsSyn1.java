package absSyn;

import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

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

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
