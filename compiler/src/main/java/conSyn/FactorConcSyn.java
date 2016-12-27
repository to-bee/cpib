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

    public FactorConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public FactorAbsSyn toAbsSyn() throws ContextError {
        return new FactorAbsSyn(subType.toAbsSyn());
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.IMAGINARY_PART) {
//            TODO: subType = new FactorImaginaryPartConcSyn();
            consume();
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.LITERAL) {
            // TODO: this.getTokenList().getCurrent().check();
            // TODO: subType = new FactorLiteralConcSyn();
            consume();
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT) {
            // TODO: subType = new FactorIdentConcSyn();
            consume();
            optionalIdentConcSyn = new OptionalIdentConcSyn(getTokenList(), getCounter());
            parseNext(optionalIdentConcSyn);
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.ADDOPR
                || getTokenList().getCurrent().getTerminal() == Terminal.NOT) {
            subType = new FactorMoniadicConcSyn(getTokenList(), getCounter());
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.LPAREN) {
            subType = new FactorExpressionConcSyn(getTokenList(), getCounter());
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.IMAG) {
            subType = new ComplImagConcSyn(getTokenList(), getCounter());
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.REAL) {
            subType = new ComplRealConcSyn(getTokenList(), getCounter());
        } else {
            throwGrammarError();
        }

        this.parseNext(subType);
    }
}
