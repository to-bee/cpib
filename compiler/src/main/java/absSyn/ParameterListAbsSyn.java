package absSyn;
import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.token.IToken;
import virtualmachineFS2015.ICodeArray;

import java.util.List;
/**
 * Created by ylaub on 26.12.2016.
 */
public class ParameterListAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private OptionalParametersAbsSyn optionalParametersAbsSyn;
    private final Terminal terminal;

    public ParameterListAbsSyn(OptionalParametersAbsSyn optionalParametersAbsSyn, Terminal terminal) {

        this.optionalParametersAbsSyn = optionalParametersAbsSyn;
        this.terminal = terminal;
    }

    public String toString(int counter) {
        return "ParameterListAbsSyns:\r\n\t" + optionalParametersAbsSyn.toString(counter)
                + "," + this.terminal;
    }


    @Override
    public void check() throws ContextError {
        this.optionalParametersAbsSyn.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
