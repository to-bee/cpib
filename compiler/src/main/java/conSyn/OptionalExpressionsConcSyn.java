package conSyn;

import absSyn.OptionalExpressionsAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class OptionalExpressionsConcSyn extends AbstractConcSyn implements IConcSyn {
    private IConcSyn subType;

    public OptionalExpressionsConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public OptionalExpressionsAbsSyn toAbsSyn() throws ContextError {
        return new OptionalExpressionsAbsSyn(subType.toAbsSyn());
    }

    /**
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.RPAREN) {
            subType = new EmptyConcSyn(getTokenList(), getCounter());
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.IMAGINARY_PART
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
            subType = new OptionalExpressionsConcSyn1(getTokenList(), getCounter());
        } else {
            throwGrammarError();
        }
        if (subType != null) {
            parseNext(subType);
        } else {
            throwGrammarError();
        }
    }
}
