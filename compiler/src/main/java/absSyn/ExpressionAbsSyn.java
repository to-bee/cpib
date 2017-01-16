package absSyn;

import context.DefaultVar;
import context.TupleVar;
import context.Var;
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

    public ExpressionAbsSyn(IToken token, Term1AbsSyn term1AbsSyn, RepBoolOprTerm1AbsSyn repBoolOprTerm1AbsSyn, Terminal terminal) {
        this.token = token;
        this.term1AbsSyn = term1AbsSyn;
        this.repBoolOprTerm1AbsSyn = repBoolOprTerm1AbsSyn;
    }

    public String toString(int counter) {
        return "";
        //return "cmdExpressionAbsSyn:\r\n\t" + term1AbsSyn.toString(counter) + "," + repBoolOprTerm1AbsSyn.toString(counter)+ "," + this.terminal;
    }

    @Override
    public void check() throws ContextError {
        Var currentVariable = Var.getCurrentVariable();
        if(currentVariable != null && currentVariable instanceof DefaultVar) {
            DefaultVar defaultVar = (DefaultVar) currentVariable;
            defaultVar.addExprVariable(this.token);
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
