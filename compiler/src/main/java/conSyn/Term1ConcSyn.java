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

    public Term1ConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public Term1AbsSyn toAbsSyn()throws ContextError {
        return new Term1AbsSyn(term2ConcSyn.toAbsSyn(), repRelOprTerm2ConcSyn.toAbsSyn());
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
                || getTokenList().getCurrent().getTerminal() == Terminal.NOT
                || getTokenList().getCurrent().getTerminal() == Terminal.IDENT
                || getTokenList().getCurrent().getTerminal() == Terminal.LITERAL) {

            term2ConcSyn = new Term2ConcSyn(getTokenList(), getCounter());
            parseNext(term2ConcSyn);

            repRelOprTerm2ConcSyn = new RepRelOprTerm2ConcSyn(getTokenList(), getCounter());
            parseNext(repRelOprTerm2ConcSyn);
        } else {
            throwGrammarError();
        }
    }
}
