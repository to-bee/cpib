package conSyn;

import absSyn.Term3AbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.token.IToken;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class Term3ConcSyn extends AbstractConcSyn implements IConcSyn {
    private FactorConcSyn factorConcSyn;
    private RepMultOprFactorConcSyn repMultOprFactorConcSyn;
    private IToken token;

    public Term3ConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public Term3AbsSyn toAbsSyn() throws ContextError {
        return new Term3AbsSyn(factorConcSyn.toAbsSyn(), repMultOprFactorConcSyn.toAbsSyn(), token);
    }

    @Override
    public void parse() throws GrammarError {
        this.token = getTokenList().getCurrent();
        Terminal terminal = getTokenList().getCurrent().getTerminal();
        if (terminal == Terminal.IMAGINARY_PART
                || terminal == Terminal.REAL
                || terminal == Terminal.IMAG
                || terminal == Terminal.LPAREN
                || terminal == Terminal.ADDOPR
                || terminal == Terminal.MINOPR
                || terminal == Terminal.MULTOPR
                || terminal == Terminal.DIVOPR
                || terminal == Terminal.MODOPR
                || terminal == Terminal.COMPLEMENT
                || terminal == Terminal.IDENT
                || terminal == Terminal.LITERAL) {
            factorConcSyn = new FactorConcSyn(getTokenList(), getCounter());
            parseNext(factorConcSyn);

            repMultOprFactorConcSyn = new RepMultOprFactorConcSyn(getTokenList(), getCounter());
            parseNext(repMultOprFactorConcSyn);
        } else {
            throwGrammarError();
        }
    }
}
