package conSyn;

import absSyn.IAbsSyn;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

import absSyn.RepeatingOptionalExpressionsAbsSyn;
import scanner.token.IToken;
import scanner.tokenList.ITokenList;
import java.util.List;
/**
 * Created by tobi on 17.12.16.
 */
public class RepeatingOptionalExpressionsConcSyn extends AbstractConcSyn implements IConcSyn {
    public RepeatingOptionalExpressionsConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    private IToken token;
    @Override
    public IAbsSyn toAbsSyn() throws ContextError {
        //Für jedes Nichtterminalsymbol (unten mit ParseNext deklariert) wird eine Liste mit den dazugehörigen Elementen dem Abstrakten Syntaxbaum übergeben.
        List<IAbsSyn> ExpressionConcSyn = super.getListByType(ExpressionConcSyn.class);
        List<IAbsSyn> RepeatingOptionalExpressionsConcSyn = super.getListByType(RepeatingOptionalExpressionsConcSyn.class);

        return new RepeatingOptionalExpressionsAbsSyn(token, ExpressionConcSyn, RepeatingOptionalExpressionsConcSyn);
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.RPAREN) {

        } else if (getTokenList().getCurrent().getTerminal() == Terminal.COMMA) {
            consume();
            parseNext(new ExpressionConcSyn(getTokenList(), getCounter()));
            parseNext(new RepeatingOptionalExpressionsConcSyn(getTokenList(), getCounter()));
        } else {
            throwGrammarError();
        }
    }
}
