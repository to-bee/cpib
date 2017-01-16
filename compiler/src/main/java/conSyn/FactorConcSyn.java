package conSyn;

import absSyn.FactorAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.token.IToken;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class FactorConcSyn extends AbstractConcSyn implements IConcSyn {
    private IConcSyn subType;
    private IToken leftToken;
    private IToken rightToken;

    public FactorConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public FactorAbsSyn toAbsSyn() throws ContextError {
        return new FactorAbsSyn(leftToken, rightToken, subType.toAbsSyn());
    }

    @Override
    public void parse() throws GrammarError {
        this.leftToken = this.getTokenList().getCurrent();
        Terminal terminal = this.leftToken.getTerminal();
        if (terminal == Terminal.IMAGINARY_PART) {
            subType = new FactorImaginaryPartConcSyn(getTokenList(), getCounter());
        } else if (terminal == Terminal.LITERAL) {
            subType = new FactorLiteralConcSyn(getTokenList(), getCounter());
        } else if (terminal == Terminal.IDENT) {
            subType = new FactorIdentConcSyn(getTokenList(), getCounter());
        } else if (terminal == Terminal.ADDOPR
                || terminal == Terminal.MINOPR
                || terminal == Terminal.MULTOPR
                || terminal == Terminal.DIVOPR
                || terminal == Terminal.COMPLEMENT) {
            subType = new FactorMonadicConcSyn(getTokenList(), getCounter());
        } else if (terminal == Terminal.LPAREN) {
            subType = new FactorExpressionConcSyn(getTokenList(), getCounter());
        } else if (terminal == Terminal.IMAG) {
            subType = new ComplImagConcSyn(getTokenList(), getCounter());
        } else if (terminal == Terminal.REAL) {
            subType = new ComplRealConcSyn(getTokenList(), getCounter());
        } else {
            throwGrammarError();
        }

        this.parseNext(subType);
        this.rightToken = this.getTokenList().getCurrent();
    }
}
