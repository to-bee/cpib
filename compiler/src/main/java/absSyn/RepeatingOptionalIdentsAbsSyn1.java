package absSyn;

import scanner.errors.ContextError;
import scanner.token.Ident;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 28.12.2016.
 */
public class RepeatingOptionalIdentsAbsSyn1 extends AbstractAbsSyn implements IAbsSyn {

    private final Ident ident;
    private final IdentsAbsSyn identsAbsSyn;

    public RepeatingOptionalIdentsAbsSyn1(Ident ident, IdentsAbsSyn identsAbsSyn) {

        this.ident = ident;
        this.identsAbsSyn = identsAbsSyn;
    }

    public String toString(int counter) {
        return "RepeatingOptionalIdentsAbsSyn1:\r\n\t" + this.ident + "," + identsAbsSyn.toString(counter);
    }

    @Override
    public void check() throws ContextError {
        ident.check();
        identsAbsSyn.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
