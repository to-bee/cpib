package absSyn;

import context.Variable;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.token.IToken;
import scanner.token.Ident;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by tobi on 17.12.16.
 */
public class ProgramAbsSyn extends AbstractAbsSyn implements IAbsSyn {
    private final OptionalGlobalDeclarationsAbsSyn optionalGlobalDeclarationList;
    private final ProgramParameterListAbsSyn programParameterList;
    private final BlockCmdAbsSyn blockCmdConcSyn;
    /**
     * Programs identifier
     */
    private Ident ident;

    public ProgramAbsSyn(Ident ident, ProgramParameterListAbsSyn programParameterListConcSyn, OptionalGlobalDeclarationsAbsSyn optionalGlobalDeclarationsConcSyn, BlockCmdAbsSyn blockCmdConcSyn) {
        this.ident = ident;
        this.optionalGlobalDeclarationList = optionalGlobalDeclarationsConcSyn;
        this.programParameterList = programParameterListConcSyn;
        this.blockCmdConcSyn = blockCmdConcSyn;
    }

    @Override
    public void check() throws ContextError {
        // Clear existing vars
        Variable.clearVariables();

        if (this.ident.getValue().length() > 256) {
            throw new ContextError("Ident too long");
        }
        optionalGlobalDeclarationList.check();
        programParameterList.check();
        blockCmdConcSyn.check();

        // Check if left type match with right type
        for (Variable var : Variable.getVariables()) {
            Terminal[] allowedTypes = null;
            switch (var.getLeftSideType()) {
                case COMPL:
                    allowedTypes = new Terminal[]{Terminal.COMPL, Terminal.INT32};
                    break;
                case INT32:
                    allowedTypes = new Terminal[]{Terminal.INT32};
                    break;
                case BOOL:
                    allowedTypes = new Terminal[]{Terminal.BOOL};
                    break;
            }

            if (!var.rightSideContainsOnly(allowedTypes)) {
                throw new ContextError(String.format("LType and RType mismatch for variable: %s", var));
            }

            Terminal leftType = var.getLeftSideType();
            IToken opr = var.getOpr();
            if (leftType != null && opr != null && var.exprVariableContains(Terminal.COMPL)) {
                if (opr.getTerminal() == Terminal.DIVOPR
                        || opr.getTerminal() == Terminal.COMPLEMENT
                        || opr.getTerminal() == Terminal.GT
                        || opr.getTerminal() == Terminal.LT
                        || opr.getTerminal() == Terminal.GE
                        || opr.getTerminal() == Terminal.LE
                        || opr.getTerminal() == Terminal.CAND
                        || opr.getTerminal() == Terminal.COR) {
                    throw new ContextError(String.format("%s not allowed for variables of type %s", opr.getTerminal(), Terminal.COMPL));
                }
            }
        }
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }

    @Override
    public String toString() {
        return this.toString(0);
    }

    public String toString(int counter) {
        StringBuilder sb = new StringBuilder();
        String tabs = getTabs(counter);
        sb.append(String.format("%s%s: %s\n", tabs, getClass().getName(), this.ident.toString()));
        counter++;
        sb.append(optionalGlobalDeclarationList.toString(counter));
        sb.append(programParameterList.toString(counter));
        sb.append(blockCmdConcSyn.toString(counter));
        return sb.toString();
    }
}
