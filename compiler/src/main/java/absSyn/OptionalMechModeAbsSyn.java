package absSyn;

import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 27.12.2016.
 */
public class OptionalMechModeAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final Terminal terminal;

    public OptionalMechModeAbsSyn(Terminal terminal) {
        this.terminal = terminal;

    }

    @Override
    public void check() throws ContextError {

    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
