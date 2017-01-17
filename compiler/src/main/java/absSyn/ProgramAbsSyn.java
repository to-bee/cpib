package absSyn;

import context.*;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.token.IToken;
import scanner.token.Ident;
import virtualmachineFS2015.ICodeArray;

import java.util.List;

/**
 * Created by tobi on 17.12.16.
 */
public class ProgramAbsSyn extends AbstractAbsSyn implements IAbsSyn {
    private final OptionalGlobalDeclarationsAbsSyn optionalGlobalDeclarationList;
    private final ProgramParameterListAbsSyn programParameterList;
    private final BlockCmdAbsSyn blockCmdConcSyn;
    private final Terminal terminal;
    /**
     * Programs identifier
     */
    private Ident ident;

    public ProgramAbsSyn(Ident ident,
                         ProgramParameterListAbsSyn programParameterListConcSyn,
                         OptionalGlobalDeclarationsAbsSyn optionalGlobalDeclarationsConcSyn,
                         BlockCmdAbsSyn blockCmdConcSyn,
                         Terminal terminal) {
        this.ident = ident;
        this.optionalGlobalDeclarationList = optionalGlobalDeclarationsConcSyn;
        this.programParameterList = programParameterListConcSyn;
        this.blockCmdConcSyn = blockCmdConcSyn;
        this.terminal = terminal;
    }

    @Override
    public void check() throws ContextError {
        // Clear existing vars
        Context.clearVmVariables();
        DefaultVar.clearVariables();

        if (this.ident.getValue().length() > 256) {
            throw new ContextError("Ident too long");
        }
        optionalGlobalDeclarationList.check();
        programParameterList.check();
        blockCmdConcSyn.check();

        // New checks with VmVar
        for (VmVar vmVar : Context.getVmVariables()) {
            vmVar.checkAssignmentEquality();
            vmVar.checkForbiddenOperations();
        }

        // Check if left type match with right type (for standard variables)
        for (Var aVar : Var.getVariables()) {
            if(aVar instanceof DefaultVar) {
                DefaultVar var = (DefaultVar) aVar;
//                var.checkAssignmentEquality();

//                List<DefaultVar> subExprVars = var.getExprVariables();
//                for(int i = 0; i<subExprVars.size(); i++) {
//                    for (int j = i; j < subExprVars.size(); j++) {
//                        if (subExprVars.get(i).getLeftSideType() != subExprVars.get(j).getLeftSideType()) {
//                            throw new ContextError(String.format("RValues must have the same type: %s/%s", subExprVars.get(i), subExprVars.get(j)));
//                        }
//                    }
//                }

//                Terminal leftType = var.getLeftSideType();
//                IToken opr = var.getOpr();
//                if (leftType != null && opr != null && var.exprVariableContains(Terminal.COMPL)) {
//                    if (opr.getTerminal() == Terminal.DIVOPR
//                            || opr.getTerminal() == Terminal.COMPLEMENT
//                            || opr.getTerminal() == Terminal.GT
//                            || opr.getTerminal() == Terminal.LT
//                            || opr.getTerminal() == Terminal.GE
//                            || opr.getTerminal() == Terminal.LE
//                            || opr.getTerminal() == Terminal.CAND
//                            || opr.getTerminal() == Terminal.COR) {
//                        throw new ContextError(String.format("A variable with type: %s must not contain an RValue with type: %s", Terminal.COMPL, opr.getTerminal()));
//                    }
//                }
            } else if(aVar instanceof TupleVar) {
                TupleVar var = (TupleVar) aVar;
                var.checkAssignmentEquality();
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
