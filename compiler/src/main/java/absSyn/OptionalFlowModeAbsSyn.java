package absSyn;

import scanner.errors.ContextError;
import scanner.token.IToken;
import virtualmachineFS2015.ICodeArray;

import java.util.List;

/**
 * Created by ylaub on 26.12.2016.
 */
public class OptionalFlowModeAbsSyn extends AbstractAbsSyn implements IAbsSyn{

    public OptionalFlowModeAbsSyn() {
    }

    @Override
    public void check() throws ContextError {
        //nothing to check
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
