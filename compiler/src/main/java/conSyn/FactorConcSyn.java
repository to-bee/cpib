package conSyn;

import absSyn.IAbsSyn;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

/**
 * Created by tobi on 17.12.16.
 */
public class FactorConcSyn extends AbstractConcSyn implements IConcSyn {
    public FactorConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public IAbsSyn toAbsSyn() {
        //TODO: implement
        return null;
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.IMAGINARY_PART) {
            consume();
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.LITERAL) {
            consume();
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT) {
            consume();
            parseNext(new OptionalIdentConcSyn(getTokenList(), getCounter()));
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.ADDOPR || getTokenList().getCurrent().getTerminal() == Terminal.ADDOPR) {
            parseNext(new MonadictOperatorConcSyn(getTokenList(), getCounter()));
            parseNext(new FactorConcSyn(getTokenList(), getCounter()));
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.NOT) {
            parseNext(new MonadictOperatorConcSyn(getTokenList(), getCounter()));
            parseNext(new FactorConcSyn(getTokenList(), getCounter()));
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.LPAREN) {
            consume();
            parseNext(new ExpressionConcSyn(getTokenList(), getCounter()));
            parseNext(new RepeatingOptionalExpressionsConcSyn(getTokenList(), getCounter()));
            if (getTokenList().getCurrent().getTerminal() == Terminal.RPAREN) {
                consume();
            } else {
                throwGrammarError();
            }
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.IMAG) {
            parseNext(new ComplImagConcSyn(getTokenList(), getCounter()));
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.REAL) {
            parseNext(new ComplRealConcSyn(getTokenList(), getCounter()));
        } else {
            throwGrammarError();
        }
    }
}
