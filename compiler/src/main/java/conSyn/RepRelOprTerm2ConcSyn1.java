package conSyn;

import absSyn.RepRelOprTerm2AbsSyn1;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.token.IToken;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class RepRelOprTerm2ConcSyn1 extends AbstractConcSyn implements IConcSyn {
    private Term2ConcSyn term2ConcSyn;
    private RepRelOprTerm2ConcSyn repRelOprTerm2ConcSyn;
    private IToken relOpr;
    private IToken relOprVariableRight;

    public RepRelOprTerm2ConcSyn1(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public RepRelOprTerm2AbsSyn1 toAbsSyn() throws ContextError {
        return new RepRelOprTerm2AbsSyn1(relOpr, relOprVariableRight, term2ConcSyn.toAbsSyn(), repRelOprTerm2ConcSyn.toAbsSyn());

    }

    /**
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        relOpr = getTokenList().getCurrent();
        consume();

        relOprVariableRight = getTokenList().getCurrent();
        term2ConcSyn = new Term2ConcSyn(getTokenList(), getCounter());
        parseNext(term2ConcSyn);
        repRelOprTerm2ConcSyn = new RepRelOprTerm2ConcSyn(getTokenList(), getCounter());
        parseNext(repRelOprTerm2ConcSyn);
    }
}
