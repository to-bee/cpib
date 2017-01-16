package absSyn;

import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 28.12.2016.
 */
public class RepeatingOptionalDeclarationsAbsSyn1 extends AbstractAbsSyn implements IAbsSyn {
    private final DeclarationAbsSyn declarationAbsSyn;
    private final RepeatingOptionalDeclarationsAbsSyn repeatingOptionalDeclarationsAbsSyn;

    public RepeatingOptionalDeclarationsAbsSyn1(DeclarationAbsSyn declarationAbsSyn, RepeatingOptionalDeclarationsAbsSyn repeatingOptionalDeclarationsAbsSyn) {

        this.declarationAbsSyn = declarationAbsSyn;
        this.repeatingOptionalDeclarationsAbsSyn = repeatingOptionalDeclarationsAbsSyn;
    }
    public String toString(int counter) {
        return "RepeatingOptionalDeclarationsAbsSyn1:\r\n\t" + declarationAbsSyn.toString(counter) + "," + repeatingOptionalDeclarationsAbsSyn.toString(counter);
    }

    @Override
    public void check() throws ContextError {
        declarationAbsSyn.check();
        repeatingOptionalDeclarationsAbsSyn.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
