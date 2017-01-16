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

    public String toString(int counter) {
        return "FactorAbsSyn:\r\n\t" + subType.toString(counter) +"," + this.terminal;
    }

    @Override
    public void check() throws ContextError {
        //TODO Tuple Assign
        /**
         * Check whether name is left or right side expression
         * We need to set the type of the right side,
         * left side types are already set
         */
        DefaultVar currentVariable = (DefaultVar) Var.getCurrentVariable();
        Var var = Var.getVar(this.token);
        if (var == null) {
            /**
             * Loads the left side variable and sets type
             * TODO: Add other types
             */
            if (this.token.getTerminal() == Terminal.IMAGINARY_PART
                    || this.token.getTerminal() == Terminal.INT32
                    || this.token.getTerminal() == Terminal.IDENT) {
                currentVariable.addRightSideToken(this.token);
            }
//            switch(this.token.getTerminal()) {
//                case IMAGINARY_PART:
//                    currentVariable.addRightSideToken(Terminal.COMPL);
//                    break;
//                case LITERAL:
//                    currentVariable.addRightSideToken(Terminal.INT32);
//                    break;
//                case IDENT:
//                    currentVariable.addRightSideToken(Terminal.BOOL);
//                    break;
//            }
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
