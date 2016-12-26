package conSyn;

import absSyn.FactorAbsSyn;
import scanner.errors.ContextError;
import scanner.token.IToken;
import scanner.tokenList.ITokenList;
import java.util.List;
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

    private IToken token;
    @Override
    public IAbsSyn toAbsSyn() throws ContextError {
        //Für jedes Nichtterminalsymbol (unten mit ParseNext deklariert) wird eine Liste mit den dazugehörigen Elementen dem Abstrakten Syntaxbaum übergeben.
        List<IAbsSyn> OptionalIdentConcSyn = super.getListByType(OptionalIdentConcSyn.class);
        List<IAbsSyn> MonadictOperatorConcSyn = super.getListByType(MonadictOperatorConcSyn.class);
        List<IAbsSyn> FactorConcSyn = super.getListByType(FactorConcSyn.class);
        List<IAbsSyn> ExpressionConcSyn = super.getListByType(ExpressionConcSyn.class);
        List<IAbsSyn> RepeatingOptionalExpressionsConcSyn = super.getListByType(RepeatingOptionalExpressionsConcSyn.class);
        List<IAbsSyn> ComplImagConcSyn = super.getListByType(ComplImagConcSyn.class);
        List<IAbsSyn> ComplRealConcSyn = super.getListByType(ComplRealConcSyn.class);

        return new FactorAbsSyn(token, OptionalIdentConcSyn, MonadictOperatorConcSyn, FactorConcSyn, ExpressionConcSyn, RepeatingOptionalExpressionsConcSyn, ComplImagConcSyn, ComplRealConcSyn);
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
