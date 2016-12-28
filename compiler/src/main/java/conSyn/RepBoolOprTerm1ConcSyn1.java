package conSyn;

import absSyn.RepBoolOprTerm1AbsSyn1;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class RepBoolOprTerm1ConcSyn1 extends AbstractConcSyn implements IConcSyn {
    private Term1ConcSyn term1ConcSyn;
    private RepBoolOprTerm1ConcSyn repBoolOprTerm1ConcSyn;

    public RepBoolOprTerm1ConcSyn1(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public RepBoolOprTerm1AbsSyn1 toAbsSyn() throws ContextError {
        return new RepBoolOprTerm1AbsSyn1(term1ConcSyn.toAbsSyn(), repBoolOprTerm1ConcSyn.toAbsSyn());
    }

    /**
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
            consume();
            term1ConcSyn = new Term1ConcSyn(getTokenList(), getCounter());
            parseNext(term1ConcSyn);
            repBoolOprTerm1ConcSyn = new RepBoolOprTerm1ConcSyn(getTokenList(), getCounter());
            parseNext(repBoolOprTerm1ConcSyn);
    }
}
