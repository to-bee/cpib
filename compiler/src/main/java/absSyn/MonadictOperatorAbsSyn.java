package absSyn;

import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 27.12.2016.
 */
public class MonadictOperatorAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    @Override
    public void check() throws ContextError {

    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
