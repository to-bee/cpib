package absSyn;

import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.token.IToken;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 26.12.2016.
 */
public class FactorImaginaryPartAbsSyn extends AbstractAbsSyn implements IAbsSyn {


    private IToken token;

    public IToken getToken() {
        return token;
    }

    public FactorImaginaryPartAbsSyn(IToken token) {

        this.token = token;
    }

    @Override
    public void check() throws ContextError {
        if(this.token.getTerminal() == Terminal.IMAGINARY_PART) {
            CmdAssignAbsSyn.getCurrentVariable().setImaginary(true);
        }
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
