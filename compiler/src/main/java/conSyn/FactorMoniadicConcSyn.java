package conSyn;

import absSyn.CmdSkipAbsSyn;
import absSyn.FactorMoniadicAbsSyn;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class FactorMoniadicConcSyn extends AbstractConcSyn implements IConcSyn {
    private MonadictOperatorConcSyn monadictOperatorConcSyn;
    private FactorConcSyn factorConcSyn;

    public FactorMoniadicConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public FactorMoniadicAbsSyn toAbsSyn() throws ContextError {
        return new FactorMoniadicAbsSyn(monadictOperatorConcSyn.toAbsSyn(), factorConcSyn.toAbsSyn());
    }

    @Override
    public void parse() throws GrammarError {
        monadictOperatorConcSyn = new MonadictOperatorConcSyn(getTokenList(), getCounter());
        parseNext(monadictOperatorConcSyn);

        factorConcSyn = new FactorConcSyn(getTokenList(), getCounter());
        parseNext(factorConcSyn);
    }
}
