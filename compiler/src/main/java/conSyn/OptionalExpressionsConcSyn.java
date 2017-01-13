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
    private Terminal terminal;

    public OptionalExpressionsConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public OptionalExpressionsAbsSyn toAbsSyn() throws ContextError {
        return new OptionalExpressionsAbsSyn(subType.toAbsSyn(), terminal);
    }

    /**
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        this.terminal = getTokenList().getCurrent().getTerminal();
        if (this.terminal == Terminal.RPAREN) {
            subType = new EmptyConcSyn(getTokenList(), getCounter());
        } else if (this.terminal == Terminal.IMAGINARY_PART
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
