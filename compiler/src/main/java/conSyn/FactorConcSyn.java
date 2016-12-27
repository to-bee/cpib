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
    private OptionalIdentConcSyn optionalIdentConcSyn;
    private MonadictOperatorConcSyn monadictOperatorConcSyn;
    private FactorConcSyn factorConcSyn;
    private ExpressionConcSyn expressionConcSyn;
    private RepeatingOptionalExpressionsConcSyn repeatingOptionalExpressionsConcSyn;
    private ComplImagConcSyn complImagConcSyn;
    private ComplRealConcSyn complRealConcSyn;
    private IToken token;
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
            // TODO: subType = new FactorMoniadicConcSyn();
            monadictOperatorConcSyn = new MonadictOperatorConcSyn(getTokenList(), getCounter());
            parseNext(monadictOperatorConcSyn);

            factorConcSyn = new FactorConcSyn(getTokenList(), getCounter());
            parseNext(factorConcSyn);
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.LPAREN) {
            // TODO: subType = new FactorExpressionConcSyn();
            consume();

            expressionConcSyn = new ExpressionConcSyn(getTokenList(), getCounter());
            parseNext(expressionConcSyn);

            repeatingOptionalExpressionsConcSyn = new RepeatingOptionalExpressionsConcSyn(getTokenList(), getCounter())
            parseNext(repeatingOptionalExpressionsConcSyn);

            if (getTokenList().getCurrent().getTerminal() == Terminal.RPAREN) {
                consume();
            } else {
                throwGrammarError();
            }
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.IMAG) {
            // TODO: subType = new FactorImagConcSyn();
            complImagConcSyn = new ComplImagConcSyn(getTokenList(), getCounter());
            parseNext(complImagConcSyn);
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.REAL) {
            // TODO: subType = new FactorRealConcSyn();
            complRealConcSyn = new ComplRealConcSyn(getTokenList(), getCounter());
            parseNext(complRealConcSyn);
        } else {
            throwGrammarError();
        }
    }
}
