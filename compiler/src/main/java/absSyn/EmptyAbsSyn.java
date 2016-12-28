package absSyn;

import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 26.12.2016.
 */
public class EmptyAbsSyn extends AbstractAbsSyn implements IAbsSyn {


    @Override
    public void check() throws ContextError {
        //nothing to check
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
