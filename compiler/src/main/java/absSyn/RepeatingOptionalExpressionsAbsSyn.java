package absSyn;

import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 26.12.2016.
 */
public class RepeatingOptionalExpressionsAbsSyn extends AbstractAbsSyn implements IAbsSyn {
    private IAbsSyn subType;

    public RepeatingOptionalExpressionsAbsSyn(IAbsSyn subType) {
        this.subType = subType;
    }

    public String toString(int counter) {
        return "RepeatingOptionalExpressionsAbsSyn:\r\n\t" + subType.toString(counter);
    }


    @Override
    public void check() throws ContextError {
        subType.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}

