package absSyn;

import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 26.12.2016.
 */
public class CmdSkipAbsSyn extends AbstractAbsSyn implements IAbsSyn {

    public String toString(int counter) {
        return "CmdSkipAbsSyn:\r\n";
    }

    @Override
    public void check() throws ContextError {
        //nothing to check
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return location; // nothing to do
    }
}
