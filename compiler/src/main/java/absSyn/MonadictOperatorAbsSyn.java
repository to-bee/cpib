package absSyn;

import context.Variable;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.token.IToken;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 27.12.2016.
 */
public class MonadictOperatorAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private IToken token;

    public IToken getToken() {
        return token;
    }

    public MonadictOperatorAbsSyn(IToken token) {
        this.token = token;
    }

    @Override
    public void check() throws ContextError {
        // TODO: maybe wrong place, we won't need this
        if(CmdAssignAbsSyn.getCurrentVariable().getType() == Terminal.COMPL) {
            if(this.token.getTerminal() == Terminal.DIVOPR) {
                throw new ContextError(String.format("%s not allowed for variables of type %s", this.token.getTerminal(), Terminal.COMPL));
            }
        }
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
