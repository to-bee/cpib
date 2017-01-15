package absSyn;

import context.Variable;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.token.IToken;
import virtualmachineFS2015.ICodeArray;

import static scanner.datatypes.Terminal.IMAGINARY_PART;

/**
 * Created by ylaub on 26.12.2016.
 */
public class FactorAbsSyn extends AbstractAbsSyn implements IAbsSyn {
    private IToken token;
    private IAbsSyn subType;
    private final Terminal terminal;

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
        Variable currentVariable = Variable.getCurrentVariable();
        Variable var = Variable.getVar(this.token);
        if(var == null) {
            /**
             * Loads the left side variable and sets type
             * TODO: Add other types
             */
            switch(this.token.getTerminal()) {
                case IMAGINARY_PART:
                    currentVariable.addRightSideType(Terminal.COMPL);
                    break;
                case LITERAL:
                    currentVariable.addRightSideType(Terminal.INT32);
                    break;
                case IDENT:
                    currentVariable.addRightSideType(Terminal.BOOL);
                    break;
            }
        } else {
            // We just add this as a type - maybe that's enough - doesn't work compl==compl is ok, but compl==bool is not
//            currentVariable.addRightSideType(var.getLeftSideType());
            currentVariable.addExprVariable(this.token);
        }

        this.subType.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
