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
    private RepeatingOptionalExpressionsConcSyn repeatingOptionalExpressionsConcSyn;
    private ExpressionConcSyn expressionConcSyn;

    public OptionalExpressionsConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public OptionalExpressionsAbsSyn toAbsSyn() throws ContextError {
        return new OptionalExpressionsAbsSyn(expressionConcSyn.toAbsSyn(), repeatingOptionalExpressionsConcSyn.toAbsSyn());
    }

    /**
     * TODO: split up 28.12.2016
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.RPAREN) {
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.IMAGINARY_PART
                || getTokenList().getCurrent().getTerminal() == Terminal.REAL
                || getTokenList().getCurrent().getTerminal() == Terminal.IMAG
                || getTokenList().getCurrent().getTerminal() == Terminal.LPAREN
                || getTokenList().getCurrent().getTerminal() == Terminal.ADDOPR
                || getTokenList().getCurrent().getTerminal() == Terminal.MINOPR
                || getTokenList().getCurrent().getTerminal() == Terminal.NOT
                || getTokenList().getCurrent().getTerminal() == Terminal.IDENT
                || getTokenList().getCurrent().getTerminal() == Terminal.LITERAL) {

            expressionConcSyn = new ExpressionConcSyn(getTokenList(), getCounter());
            parseNext(expressionConcSyn);

            repeatingOptionalExpressionsConcSyn = new RepeatingOptionalExpressionsConcSyn(getTokenList(), getCounter());
            parseNext(repeatingOptionalExpressionsConcSyn);
        } else {
            throwGrammarError();
        }
    }
}
