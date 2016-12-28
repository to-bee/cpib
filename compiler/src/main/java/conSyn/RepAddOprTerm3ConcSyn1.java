package conSyn;

import absSyn.RepAddOprTerm3AbsSyn1;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class RepAddOprTerm3ConcSyn1 extends AbstractConcSyn implements IConcSyn {
    private Term3ConcSyn term3ConcSyn;
    private RepAddOprTerm3ConcSyn repAddOprTerm3ConcSyn;

    public RepAddOprTerm3ConcSyn1(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public RepAddOprTerm3AbsSyn1 toAbsSyn() throws ContextError {
        return new RepAddOprTerm3AbsSyn1(term3ConcSyn.toAbsSyn(), repAddOprTerm3ConcSyn.toAbsSyn());
    }

    /**
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        consume();
        term3ConcSyn = new Term3ConcSyn(getTokenList(), getCounter());
        parseNext(term3ConcSyn);
        repAddOprTerm3ConcSyn = new RepAddOprTerm3ConcSyn(getTokenList(), getCounter());
        parseNext(repAddOprTerm3ConcSyn);
    }
}
