package absSyn;

import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 26.12.2016.
 */
public class OptionalGlobalDeclarationsAbsSyn extends AbstractAbsSyn implements IAbsSyn {


    private IAbsSyn subType;

    public OptionalGlobalDeclarationsAbsSyn(IAbsSyn subType) {
        this.subType = subType;
    }

    @Override
    public void check() throws ContextError {
        subType.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }

    public String toString(int counter) {
        StringBuilder sb = new StringBuilder();
        String tabs = getTabs(counter);
        sb.append(String.format("%s%s\n", tabs, getClass().getName()));
        counter++;
        sb.append(subType.toString(counter));
        return sb.toString();
    }
}
