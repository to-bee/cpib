package conSyn;

import absSyn.FactorAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.token.IToken;
import scanner.token.Ident;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class FactorConcSyn extends AbstractConcSyn implements IConcSyn {
    private IConcSyn subType;
    private IToken token;
    private Terminal terminal;

    public FactorConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public FactorAbsSyn toAbsSyn() throws ContextError {
        return new FactorAbsSyn(token, subType.toAbsSyn());
    }

    @Override
    public void parse() throws GrammarError {
        this.token = this.getTokenList().getCurrent();
        this.terminal = getTokenList().getCurrent().getTerminal();
        if (this.terminal == Terminal.IMAGINARY_PART) {
            subType = new FactorImaginaryPartConcSyn(getTokenList(), getCounter());
        } else if (this.terminal == Terminal.LITERAL) {
            subType = new FactorLiteralConcSyn(getTokenList(), getCounter());
        } else if (this.terminal == Terminal.IDENT) {
            subType = new FactorIdentConcSyn(getTokenList(), getCounter());
        } else if (this.terminal == Terminal.ADDOPR
                || this.terminal == Terminal.MINOPR
                || this.terminal == Terminal.MULTOPR
                || this.terminal == Terminal.DIVOPR
                || this.terminal == Terminal.COMPLEMENT) {
            subType = new FactorMonadicConcSyn(getTokenList(), getCounter());
        } else if (this.terminal == Terminal.LPAREN) {
            subType = new FactorExpressionConcSyn(getTokenList(), getCounter());
        } else if (this.terminal == Terminal.IMAG) {
            subType = new ComplImagConcSyn(getTokenList(), getCounter());
        } else if (this.terminal == Terminal.REAL) {
            subType = new ComplRealConcSyn(getTokenList(), getCounter());
        } else {
            throwGrammarError();
        }

        this.parseNext(subType);
    }
}
