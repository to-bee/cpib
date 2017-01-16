package absSyn;

import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.token.IToken;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 26.12.2016.
 */
public class Term3AbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final FactorAbsSyn factorAbsSyn;
    private final RepMultOprFactorAbsSyn repMultOprFactorAbsSyn;
    private final IToken token;


    public Term3AbsSyn(FactorAbsSyn factorAbsSyn, RepMultOprFactorAbsSyn repMultOprFactorAbsSyn, IToken token) {
        this.factorAbsSyn = factorAbsSyn;
        this.repMultOprFactorAbsSyn = repMultOprFactorAbsSyn;
        this.token = token;
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
