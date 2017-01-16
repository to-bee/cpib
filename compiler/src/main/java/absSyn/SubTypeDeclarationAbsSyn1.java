package absSyn;

import context.Context;
import context.TupleVar;
import context.Var;
import context.VmVar;
import scanner.errors.ContextError;
import scanner.token.IToken;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 28.12.2016.
 */
public class SubTypeDeclarationAbsSyn1 extends AbstractAbsSyn implements IAbsSyn {
    private IToken token;

    public SubTypeDeclarationAbsSyn1(IToken token) {
        this.token = token;
    }
    public String toString(int counter) {
        return "SubTypeDeclarationAbsSyn1:\r\n\t" + this.token;
    }

    @Override
    public void check() throws ContextError {
        VmVar currentVmVar = Context.getCurrentVmVariable();
        currentVmVar.addType(this.token);

        Var currentVariable = Var.getCurrentVariable();
        if(currentVariable instanceof TupleVar) {
            TupleVar tupleVar = (TupleVar) currentVariable;
            tupleVar.addLeftSideToken(this.token);
        }
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
