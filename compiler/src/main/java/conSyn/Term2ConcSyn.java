package conSyn;

import absSyn.Term2AbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
/**
 * Created by tobi on 17.12.16.
 */
public class Term2ConcSyn extends AbstractConcSyn implements IConcSyn {
    private Term3ConcSyn term3ConcSyn;
    private RepAddOprTerm3ConcSyn repAddOprTerm3ConcSyn;
    private Terminal terminal;

    public Term2ConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public Term2AbsSyn toAbsSyn() throws ContextError {
        return new Term2AbsSyn(term3ConcSyn.toAbsSyn(), repAddOprTerm3ConcSyn.toAbsSyn());
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
            term3ConcSyn = new Term3ConcSyn(getTokenList(), getCounter());
            parseNext(term3ConcSyn);

            repAddOprTerm3ConcSyn = new RepAddOprTerm3ConcSyn(getTokenList(), getCounter());
            parseNext(repAddOprTerm3ConcSyn);
        } else {
            throwGrammarError();
        }
    }
}
