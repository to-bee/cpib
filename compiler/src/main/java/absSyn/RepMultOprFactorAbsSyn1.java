package absSyn;

import scanner.errors.ContextError;
import scanner.token.IToken;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 28.12.2016.
 */
public class RepMultOprFactorAbsSyn1 extends AbstractAbsSyn implements IAbsSyn {
    private IToken token;
    private final FactorAbsSyn factorAbsSyn;
    private final RepMultOprFactorAbsSyn repMultOprFactorAbsSyn;

    public RepMultOprFactorAbsSyn1(IToken token, FactorAbsSyn factorAbsSyn, RepMultOprFactorAbsSyn repMultOprFactorAbsSyn) {
        this.token = token;
        this.factorAbsSyn = factorAbsSyn;
        this.repMultOprFactorAbsSyn = repMultOprFactorAbsSyn;
    }

    @Override
    public void check() throws ContextError {
        // TODO: move this maybe
//        if(DefaultVar.getExprVariableLeft().rightSideTypeContains(Terminal.COMPL)) {
//            if(this.token.getTerminal() == Terminal.DIVOPR) {
//                throw new ContextError(String.format("%s not allowed for variables of type %s", this.token.getTerminal(), Terminal.COMPL));
//            }
//        }

        factorAbsSyn.check();
        repMultOprFactorAbsSyn.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
