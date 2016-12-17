package conSyn;

import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

/**
 * Created by tobi on 17.12.16.
 */
public class Factor extends AbstractConcSyn implements IConcSyn {
    public Factor(ITokenList tokenList) {
        super(tokenList);
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.IMAGINARY_PART) {
            consume();
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.LITERAL) {
            consume();
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT) {
            consume();
            parseNext(new OptionalIdent(getTokenList()));
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.ADDOPR || getTokenList().getCurrent().getTerminal() == Terminal.ADDOPR) {
            parseNext(new MonadictOperator(getTokenList()));
            parseNext(new Factor(getTokenList()));
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.NOT) {
            parseNext(new MonadictOperator(getTokenList()));
            parseNext(new Factor(getTokenList()));
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.LPAREN) {
            consume();
            parseNext(new Expression(getTokenList()));
            if (getTokenList().getCurrent().getTerminal() == Terminal.RPAREN) {
                consume();
            } else {
                throwGrammarError();
            }
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.IMAG) {
            parseNext(new ComplImag(getTokenList()));
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.REAL) {
            parseNext(new ComplReal(getTokenList()));
        } else {
            throwGrammarError();
        }
    }
}
