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

    public Term2ConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public Term2AbsSyn toAbsSyn() throws ContextError {
        return new Term2AbsSyn(term3ConcSyn.toAbsSyn(), repAddOprTerm3ConcSyn.toAbsSyn());
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.IMAGINARY_PART
                || getTokenList().getCurrent().getTerminal() == Terminal.REAL
                || getTokenList().getCurrent().getTerminal() == Terminal.IMAG
                || getTokenList().getCurrent().getTerminal() == Terminal.LPAREN
                || getTokenList().getCurrent().getTerminal() == Terminal.ADDOPR
                || getTokenList().getCurrent().getTerminal() == Terminal.MINOPR
                || getTokenList().getCurrent().getTerminal() == Terminal.MULTOPR
                || getTokenList().getCurrent().getTerminal() == Terminal.DIVOPR
                || getTokenList().getCurrent().getTerminal() == Terminal.MODOPR
                || getTokenList().getCurrent().getTerminal() == Terminal.COMPLEMENT
                || getTokenList().getCurrent().getTerminal() == Terminal.IDENT
                || getTokenList().getCurrent().getTerminal() == Terminal.LITERAL) {
            term3ConcSyn = new Term3ConcSyn(getTokenList(), getCounter());
            parseNext(term3ConcSyn);

            repAddOprTerm3ConcSyn = new RepAddOprTerm3ConcSyn(getTokenList(), getCounter());
            parseNext(repAddOprTerm3ConcSyn);
        } else {
            throwGrammarError();
        }
    }
}
