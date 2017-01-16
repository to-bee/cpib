package absSyn;

import context.DefaultVar;
import context.Var;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.token.IToken;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 26.12.2016.
 */
public class FactorLiteralAbsSyn extends AbstractAbsSyn implements IAbsSyn {


    private IToken token;

    public IToken getToken() {
        return token;
    }

    public FactorLiteralAbsSyn(IToken token) {

        this.token = token;
    }


    public String toString(int counter) {
        return "FactorLiteralAbsSyn:\r\n\t" + this.token.getTerminal();
    }

    @Override
    public void check() throws ContextError {
        if(((DefaultVar) Var.getCurrentVariable()).rightSideTypeContains(Terminal.COMPL)) {
            if(this.token.getTerminal() == Terminal.COMPLEMENT || this.token.getTerminal() == Terminal.IDENT) {
                throw new ContextError(String.format("%s not allowed for variables of type %s", this.token.getTerminal(), Terminal.COMPL));
            }
        }
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
