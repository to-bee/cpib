package absSyn;

import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 26.12.2016.
 */
public class OptionalGlobalImportsAbsSyn extends AbstractAbsSyn implements IAbsSyn {

    private IAbsSyn subType;
    private final Terminal terminal;

    public OptionalGlobalImportsAbsSyn(IAbsSyn subType, Terminal terminal) {

        this.subType = subType;
        this.terminal = terminal;
    }

    public String toString(int counter) {
        return "OptionalFlowModeAbsSyn:\r\n\t" + subType.toString(counter) +
              "," + this.terminal;
    }

    @Override
    public void check() throws ContextError {
        subType.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return location; // nothing to do
    }
}
