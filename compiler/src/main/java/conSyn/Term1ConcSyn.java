package conSyn;

import absSyn.Term1AbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
/**
 * Created by tobi on 17.12.16.
 */
public class Term1ConcSyn extends AbstractConcSyn implements IConcSyn {
    private Term2ConcSyn term2ConcSyn;
    private RepRelOprTerm2ConcSyn repRelOprTerm2ConcSyn;
    private Terminal terminal;

    public Term1ConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public Term1AbsSyn toAbsSyn()throws ContextError {
        return new Term1AbsSyn(term2ConcSyn.toAbsSyn(), repRelOprTerm2ConcSyn.toAbsSyn(), terminal);
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

            term2ConcSyn = new Term2ConcSyn(getTokenList(), getCounter());
            parseNext(term2ConcSyn);

            repRelOprTerm2ConcSyn = new RepRelOprTerm2ConcSyn(getTokenList(), getCounter());
            parseNext(repRelOprTerm2ConcSyn);
        } else {
            throwGrammarError();
        }
    }
}
