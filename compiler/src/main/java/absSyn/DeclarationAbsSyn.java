package absSyn;
import conSyn.FunctionDeclarationConcSyn;
import conSyn.IConcSyn;
import conSyn.ProcedureDeclarationConcSyn;
import conSyn.StorageDeclarationConcSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.token.IToken;
import virtualmachineFS2015.ICodeArray;

import java.util.List;
/**
 * Created by ylaub on 26.12.2016.
 */
public class DeclarationAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private IAbsSyn subType;
    private final Terminal terminal;

    public DeclarationAbsSyn(IAbsSyn subType, Terminal terminal) {
        this.subType = subType;
        this.terminal = terminal;
    }

    @Override
    public void check() throws ContextError {
        subType.check();
    }

    public String toString(int counter) {
        return "DeclarationAbsSyn:\r\n\t" + subType.toString(counter)
                + "," + this.terminal;
    }


    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}

