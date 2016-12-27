package conSyn;

import absSyn.CmdAssignAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class CmdAssignConcSyn extends AbstractConcSyn implements IConcSyn {
    private ExpressionConcSyn exprL;
    private ExpressionConcSyn exprR;

    public CmdAssignConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public CmdAssignAbsSyn toAbsSyn() throws ContextError {
        return new CmdAssignAbsSyn(exprL.toAbsSyn(), exprR.toAbsSyn());
    }

    @Override
    public void parse() throws GrammarError {
        exprL = new ExpressionConcSyn(getTokenList(), getCounter());
        parseNext(exprL);
        if (getTokenList().getCurrent().getTerminal() == Terminal.BECOMES) {
            consume();
            exprR = new ExpressionConcSyn(getTokenList(), getCounter());
            parseNext(exprR);
        } else {
            throwGrammarError();
        }

    }
}
