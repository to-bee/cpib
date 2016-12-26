package conSyn;

import absSyn.IAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import absSyn.ComplImagAbsSyn;
import scanner.token.IToken;
import scanner.tokenList.ITokenList;
import java.util.List;

/**
 * Created by tobi on 17.12.16.
 */
public class ComplImagConcSyn extends AbstractConcSyn implements IConcSyn {
    public ComplImagConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    private IToken token;
    @Override
    public IAbsSyn toAbsSyn() throws ContextError {
        //Für jedes Nichtterminalsymbol (unten mit ParseNext deklariert) wird eine Liste mit den dazugehörigen Elementen dem Abstrakten Syntaxbaum übergeben.
        List<IAbsSyn> ExpressionConcSyn = super.getListByType(ExpressionConcSyn.class);

        return new ComplImagAbsSyn(token, ExpressionConcSyn);
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.IMAG) {
            consume();
            if (getTokenList().getCurrent().getTerminal() == Terminal.LPAREN) {
                consume();
                this.parseNext(new ExpressionConcSyn(this.getTokenList(), getCounter()));
                if (getTokenList().getCurrent().getTerminal() == Terminal.RPAREN) {
                    consume();
                } else {
                    throwGrammarError();
                }
            } else {
                throwGrammarError();
            }
        } else {
            throwGrammarError();
        }
    }
}
