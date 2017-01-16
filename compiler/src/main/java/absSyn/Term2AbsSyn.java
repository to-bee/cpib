package absSyn;

import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 26.12.2016.
 */
public class Term2AbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final Term3AbsSyn term3AbsSyn;
    private final RepAddOprTerm3AbsSyn repAddOprTerm3AbsSyn;
    private final Terminal terminal;


    public Term2AbsSyn(Term3AbsSyn term3AbsSyn, RepAddOprTerm3AbsSyn repAddOprTerm3AbsSyn, Terminal terminal) {
        this.term3AbsSyn = term3AbsSyn;
        this.repAddOprTerm3AbsSyn = repAddOprTerm3AbsSyn;
        this.terminal = terminal;
    }

    public String toString(int counter) {
        return "Term2AbsSyn:\r\n\t" + term3AbsSyn.toString(counter) + "," + repAddOprTerm3AbsSyn.toString(counter)+ "," + this.terminal;
    }

    @Override
    public void check() throws ContextError {
        this.term3AbsSyn.check();
        this.repAddOprTerm3AbsSyn.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}

