package absSyn;

import context.*;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.token.IToken;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 26.12.2016.
 */
public class FactorAbsSyn extends AbstractAbsSyn implements IAbsSyn {
    private IToken leftToken;
    private IToken rightToken;
    private IAbsSyn subType;

    public FactorAbsSyn(IToken leftToken, IToken rightToken, IAbsSyn subType) {
        this.leftToken = leftToken;
        this.rightToken = rightToken;
        this.subType = subType;
    }

    @Override
    public void check() throws ContextError {
        if(rightToken.getTerminal() != Terminal.BECOMES) {
            VmVar currentVmVar = Context.getCurrentVmVariable();
            Assignment currentAssignment = currentVmVar.getCurrentAssignment();
            if(leftToken.getTerminal() == Terminal.LPAREN) {
                // Create sub assignment and set current to it
                Assignment subAssignment = new Assignment(currentAssignment);
                currentAssignment.addComponent(subAssignment);
                currentVmVar.setCurrentAssignment(subAssignment);
            } else {
                currentAssignment.addComponent(this.leftToken);
            }

            if(rightToken.getTerminal() == Terminal.RPAREN) {
                // Set parent as current assignment
                currentVmVar.setCurrentAssignment(currentAssignment.getParent());
            } else {
                currentAssignment.addComponent(this.rightToken);
            }
        }


        //TODO Tuple Assign
        /**
         * Check whether name is left or right side expression
         * We need to set the type of the right side,
         * left side types are already set
         */
        Var currentVariable = Var.getCurrentVariable();
        Var var = Var.getVar(this.leftToken);
        if (var == null) {
            /**
             * Loads the left side variable and sets type
             * TODO: Add other types
             */
            if (this.leftToken.getTerminal() == Terminal.IMAGINARY_PART
                    || this.leftToken.getTerminal() == Terminal.INT32
                    || this.leftToken.getTerminal() == Terminal.LITERAL
                    || this.leftToken.getTerminal() == Terminal.IDENT) {
                currentVariable.addRightSideToken(this.leftToken);
            }
        }
        // We just addVar this as a type - maybe that's enough - doesn't work compl==compl is ok, but compl==bool is not
        // currentVariable.addRightSideType(var.getLeftSideType());
        else if(currentVariable instanceof DefaultVar) {
            DefaultVar defaultVar = (DefaultVar) currentVariable;
            defaultVar.addExprVariable(this.leftToken);
        }

        this.subType.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
