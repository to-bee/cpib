package absSyn;

import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 26.12.2016.
 */
public class RepeatingOptionalStorageDeclarationsAbsSyn extends AbstractAbsSyn implements IAbsSyn {
    private IAbsSyn subType;

    public RepeatingOptionalStorageDeclarationsAbsSyn(IAbsSyn subType) {
        this.subType = subType;
    }

    @Override
    public void check() throws ContextError {
        subType.check();
    }


    public String toString(int counter) {
        return "RepeatingOptionalStorageDeclarationsAbsSyn:\r\n\t" + subType.toString(counter);
    }


    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
