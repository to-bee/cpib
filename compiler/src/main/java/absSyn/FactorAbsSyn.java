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
public class FactorAbsSyn extends AbstractAbsSyn implements IAbsSyn {
    private final Terminal terminal;
    private IToken token;
    private IAbsSyn subType;

    public FactorAbsSyn(IToken token, IAbsSyn subType, Terminal terminal) {
        this.token = token;
        this.subType = subType;
        this.terminal = terminal;
    }

    @Override
    public void check() throws ContextError {
        //TODO Tuple Assign
        /**
         * Check whether name is left or right side expression
         * We need to set the type of the right side,
         * left side types are already set
         */
        Var currentVariable = Var.getCurrentVariable();
        Var var = Var.getVar(this.token);
        if (var == null) {
            /**
             * Loads the left side variable and sets type
             * TODO: Add other types
             */
            if (this.token.getTerminal() == Terminal.IMAGINARY_PART
                    || this.token.getTerminal() == Terminal.INT32
                    || this.token.getTerminal() == Terminal.LITERAL
                    || this.token.getTerminal() == Terminal.IDENT) {
                currentVariable.addRightSideToken(this.token);
            }
        }
        // We just addVar this as a type - maybe that's enough - doesn't work compl==compl is ok, but compl==bool is not
        // currentVariable.addRightSideType(var.getLeftSideType());
        else if(currentVariable instanceof DefaultVar) {
            DefaultVar defaultVar = (DefaultVar) currentVariable;
            defaultVar.addExprVariable(this.token);
        }

        this.subType.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
