package conSyn;

import absSyn.CmdSkipAbsSyn;
import absSyn.FactorMoniadicAbsSyn;
import context.Variable;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.token.IToken;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class FactorMonadicConcSyn extends AbstractConcSyn implements IConcSyn {
    private MonadictOperatorConcSyn monadictOperatorConcSyn;
    private FactorConcSyn factorConcSyn;
    private IToken exprOpr;
    private IToken exprVariableRight;

    public FactorMonadicConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public FactorMoniadicAbsSyn toAbsSyn() throws ContextError {
        return new FactorMoniadicAbsSyn(exprOpr, exprVariableRight, monadictOperatorConcSyn.toAbsSyn(), factorConcSyn.toAbsSyn());
    }

    @Override
    public void parse() throws GrammarError {
        this.exprOpr = this.getTokenList().getCurrent();
        monadictOperatorConcSyn = new MonadictOperatorConcSyn(getTokenList(), getCounter());
        parseNext(monadictOperatorConcSyn);

        this.exprVariableRight = this.getTokenList().getCurrent();
        factorConcSyn = new FactorConcSyn(getTokenList(), getCounter());
        parseNext(factorConcSyn);
    }
}
