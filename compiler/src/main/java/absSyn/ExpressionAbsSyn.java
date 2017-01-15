package absSyn;

import context.AbstractVar;
import context.DefaultVariable;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.token.IToken;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 26.12.2016.
 */
public class ExpressionAbsSyn extends AbstractAbsSyn implements IAbsSyn{

    private final Term1AbsSyn term1AbsSyn;
    private final RepBoolOprTerm1AbsSyn repBoolOprTerm1AbsSyn;
    private final IToken token;
    private final Terminal terminal;

    public ExpressionAbsSyn(IToken token, Term1AbsSyn term1AbsSyn, RepBoolOprTerm1AbsSyn repBoolOprTerm1AbsSyn, Terminal terminal) {
        this.token = token;
        this.term1AbsSyn = term1AbsSyn;
        this.repBoolOprTerm1AbsSyn = repBoolOprTerm1AbsSyn;
        this.terminal = terminal;
    }

    @Override
    public void check() throws ContextError {
        DefaultVariable currentVariable = (DefaultVariable) AbstractVar.getCurrentVariable();
        DefaultVariable var = (DefaultVariable) DefaultVariable.getVar(this.token);
        if(var != null) {
            currentVariable.addExprVariable(this.token);
        }

        term1AbsSyn.check();
        repBoolOprTerm1AbsSyn.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }

    public IToken getToken() {
        return token;
    }

    public Term1AbsSyn getTerm1AbsSyn() {
        return term1AbsSyn;
    }

    public RepBoolOprTerm1AbsSyn getRepBoolOprTerm1AbsSyn() {
        return repBoolOprTerm1AbsSyn;
    }
}
