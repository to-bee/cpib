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

    public String toString(int counter) {
        return "OptionalChangeModeAbsSyn:\r\n\t" + this.terminal;
    }

    @Override
    public void check() throws ContextError {
        if (terminal == Terminal.VAR){
            //Standardmässig sind Variablen mit const initialisiert. --> needed by Tuples (Yves, 16.1.17)
            Var var = Var.getCurrentVariable();
            var.setConst(false);
        }
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
