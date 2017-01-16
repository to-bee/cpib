package absSyn;

import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.token.IToken;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 26.12.2016.
 */
public class FactorImaginaryPartAbsSyn extends AbstractAbsSyn implements IAbsSyn {
    public FactorImaginaryPartAbsSyn() {
    }

    @Override
    public void check() throws ContextError {

    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return location;
    }
}
