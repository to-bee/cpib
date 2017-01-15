package absSyn;

import context.Var;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 26.12.2016.
 */
public class OptionalChangeModeAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final Terminal terminal;

    public OptionalChangeModeAbsSyn(Terminal terminal) {
        this.terminal = terminal;

    }

    @Override
    public void check() throws ContextError {
        //TODO const check
        if (terminal.getValue() == "const"){
            //currentvariable gibts noch nicht...
        }
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
