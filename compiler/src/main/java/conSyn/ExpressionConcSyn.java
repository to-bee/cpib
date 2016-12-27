package conSyn;

import absSyn.ExpressionAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
/**
 * Created by tobi on 17.12.16.
 */
public class ExpressionConcSyn extends AbstractConcSyn implements IConcSyn {
    private Term1ConcSyn term1ConcSyn;
    private RepBoolOprTerm1ConcSyn repBoolOprTerm1ConcSyn;

    public ExpressionConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public ExpressionAbsSyn toAbsSyn() throws ContextError {
        return new ExpressionAbsSyn(term1ConcSyn.toAbsSyn(),repBoolOprTerm1ConcSyn.toAbsSyn());
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
            term1ConcSyn = new Term1ConcSyn(getTokenList(), getCounter());
            parseNext(term1ConcSyn);
            repBoolOprTerm1ConcSyn = new RepBoolOprTerm1ConcSyn(getTokenList(), getCounter());
            parseNext(repBoolOprTerm1ConcSyn);
        } else {
            throwGrammarError();
        }
    }
}
