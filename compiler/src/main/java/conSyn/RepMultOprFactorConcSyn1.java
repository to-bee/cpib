package conSyn;

import absSyn.RepMultOprFactorAbsSyn1;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class RepMultOprFactorConcSyn1 extends AbstractConcSyn implements IConcSyn {
    private FactorConcSyn factorConcSyn;
    private RepMultOprFactorConcSyn repMultOprFactorConcSyn;

    public RepMultOprFactorConcSyn1(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public RepMultOprFactorAbsSyn1 toAbsSyn() throws ContextError {
        return new RepMultOprFactorAbsSyn1(factorConcSyn.toAbsSyn(), repMultOprFactorConcSyn.toAbsSyn());
    }

    /**
     * TODO
     *
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        consume();
        factorConcSyn = new FactorConcSyn(getTokenList(), getCounter());
        parseNext(factorConcSyn);

        repMultOprFactorConcSyn = new RepMultOprFactorConcSyn(getTokenList(), getCounter());
        parseNext(repMultOprFactorConcSyn);
    }
}
