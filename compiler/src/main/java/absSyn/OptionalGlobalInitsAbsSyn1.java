package absSyn;

import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 28.12.2016.
 */
public class OptionalGlobalInitsAbsSyn1 extends AbstractAbsSyn implements IAbsSyn {
    private IdentsAbsSyn identsAbsSyn;

    public OptionalGlobalInitsAbsSyn1(IdentsAbsSyn identsAbsSyn) {

        this.identsAbsSyn = identsAbsSyn;
    }

    public String toString(int counter) {
        return "OptionalGlobalInitsAbsSyn1:\r\n\t" + identsAbsSyn.toString(counter);
    }

    @Override
    public void check() throws ContextError {
        identsAbsSyn.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
