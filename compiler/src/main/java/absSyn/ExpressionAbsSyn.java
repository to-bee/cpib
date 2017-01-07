package absSyn;

import context.Type;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.token.IToken;
import scanner.token.Ident;
import scanner.token.Literal;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 26.12.2016.
 */
public class ExpressionAbsSyn extends AbstractAbsSyn implements IAbsSyn{

    private final Term1AbsSyn term1AbsSyn;
    private final RepBoolOprTerm1AbsSyn repBoolOprTerm1AbsSyn;
    private final IToken token;

    public ExpressionAbsSyn(IToken token, Term1AbsSyn term1AbsSyn, RepBoolOprTerm1AbsSyn repBoolOprTerm1AbsSyn) {
        this.token = token;
        this.term1AbsSyn = term1AbsSyn;
        this.repBoolOprTerm1AbsSyn = repBoolOprTerm1AbsSyn;
    }

    @Override
    public void check() throws ContextError {
        if(CmdAssignAbsSyn.getCurrentVariable().getType() == Terminal.COMPL) {
            if(this.token.getTerminal() == Terminal.IMAGINARY_PART) {
                CmdAssignAbsSyn.getCurrentVariable().setImaginary(true);
            }
            else if(this.token.getTerminal() == Terminal.DIVOPR
                    || this.token.getTerminal() == Terminal.NOT) {
                throw new ContextError(String.format("%s not allowed for variables of type %s", this.token.getTerminal(), Terminal.COMPL));
            }
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
