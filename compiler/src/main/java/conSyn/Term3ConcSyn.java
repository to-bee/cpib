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
    private Terminal terminal;

    public Term3ConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public Term3AbsSyn toAbsSyn() throws ContextError {
        return new Term3AbsSyn(factorConcSyn.toAbsSyn(), repMultOprFactorConcSyn.toAbsSyn());
    }

    @Override
    public void parse() throws GrammarError {
        this.terminal = getTokenList().getCurrent().getTerminal();
        if (this.terminal == Terminal.IMAGINARY_PART
                || this.terminal == Terminal.REAL
                || this.terminal == Terminal.IMAG
                || this.terminal == Terminal.LPAREN
                || this.terminal == Terminal.ADDOPR
                || this.terminal == Terminal.MINOPR
                || this.terminal == Terminal.MULTOPR
                || this.terminal == Terminal.DIVOPR
                || this.terminal == Terminal.MODOPR
                || this.terminal == Terminal.COMPLEMENT
                || this.terminal == Terminal.IDENT
                || this.terminal == Terminal.LITERAL) {
            factorConcSyn = new FactorConcSyn(getTokenList(), getCounter());
            parseNext(factorConcSyn);

            repMultOprFactorConcSyn = new RepMultOprFactorConcSyn(getTokenList(), getCounter());
            parseNext(repMultOprFactorConcSyn);
        } else {
            throwGrammarError();
        }
    }
}
