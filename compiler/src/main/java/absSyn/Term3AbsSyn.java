package absSyn;

import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 26.12.2016.
 */
public class Term3AbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final FactorAbsSyn factorAbsSyn;
    private final RepMultOprFactorAbsSyn repMultOprFactorAbsSyn;
    private final Terminal terminal;


    public Term3AbsSyn(FactorAbsSyn factorAbsSyn, RepMultOprFactorAbsSyn repMultOprFactorAbsSyn, Terminal terminal) {
        this.factorAbsSyn = factorAbsSyn;
        this.repMultOprFactorAbsSyn = repMultOprFactorAbsSyn;
        this.terminal = terminal;
    }

    public String toString(int counter) {
        return "Term3AbsSyn:\r\n\t" + factorAbsSyn.toString(counter) + "," + repMultOprFactorAbsSyn.toString(counter)+ "," + this.terminal;
    }

    @Override
    public void check() throws ContextError {
        this.factorAbsSyn.check();
        this.repMultOprFactorAbsSyn.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
