package conSyn;

import absSyn.Term3AbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class Term3ConcSyn extends AbstractConcSyn implements IConcSyn {
    private FactorConcSyn factorConcSyn;
    private RepMultOprFactorConcSyn repMultOprFactorConcSyn;

    public Term3ConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public Term3AbsSyn toAbsSyn() throws ContextError {
        return new Term3AbsSyn(factorConcSyn.toAbsSyn(), repMultOprFactorConcSyn.toAbsSyn());
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.IMAGINARY_PART
                || getTokenList().getCurrent().getTerminal() == Terminal.REAL
                || getTokenList().getCurrent().getTerminal() == Terminal.IMAG
                || getTokenList().getCurrent().getTerminal() == Terminal.LPAREN
                || getTokenList().getCurrent().getTerminal() == Terminal.ADDOPR
                || getTokenList().getCurrent().getTerminal() == Terminal.MINOPR
                || getTokenList().getCurrent().getTerminal() == Terminal.NOT
                || getTokenList().getCurrent().getTerminal() == Terminal.IDENT
                || getTokenList().getCurrent().getTerminal() == Terminal.LITERAL) {
            factorConcSyn = new FactorConcSyn(getTokenList(), getCounter());
            parseNext(factorConcSyn);

            repMultOprFactorConcSyn = new RepMultOprFactorConcSyn(getTokenList(), getCounter());
            parseNext(repMultOprFactorConcSyn);
        } else {
            throwGrammarError();
        }
    }
}
