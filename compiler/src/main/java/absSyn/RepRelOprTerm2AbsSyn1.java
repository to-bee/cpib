package absSyn;

import context.DefaultVar;
import context.Var;
import scanner.errors.ContextError;
import scanner.token.IToken;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 28.12.2016.
 */
public class RepRelOprTerm2AbsSyn1 extends AbstractAbsSyn implements IAbsSyn {
    private IToken relOpr;
    private IToken relOprVariableRight;
    private final Term2AbsSyn term2AbsSyn;
    private final RepRelOprTerm2AbsSyn repRelOprTerm2AbsSyn;

    public RepRelOprTerm2AbsSyn1(IToken relOpr, IToken relOprVariableRight, Term2AbsSyn term2AbsSyn, RepRelOprTerm2AbsSyn repRelOprTerm2AbsSyn) {
        this.relOpr = relOpr;
        this.relOprVariableRight = relOprVariableRight;
        this.term2AbsSyn = term2AbsSyn;
        this.repRelOprTerm2AbsSyn = repRelOprTerm2AbsSyn;
    }

    public String toString(int counter) {
        return "RepRelOprTerm2AbsSyn1:\r\n\t" + relOpr.getTerminal() + "," + relOprVariableRight.getTerminal() +"," + term2AbsSyn.toString(counter) + "," + repRelOprTerm2AbsSyn.toString(counter);
    }

    @Override
    public void check() throws ContextError {
        DefaultVar currentVariable = (DefaultVar) Var.getCurrentVariable();
        currentVariable.setExprOpr(this.relOpr);
        DefaultVar var = (DefaultVar) Var.getVar(this.relOprVariableRight);
        if(var != null) {
            currentVariable.addExprVariable(this.relOprVariableRight);
        }

        term2AbsSyn.check();
        repRelOprTerm2AbsSyn.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
