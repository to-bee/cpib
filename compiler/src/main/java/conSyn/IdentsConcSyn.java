package conSyn;

import absSyn.IAbsSyn;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

import absSyn.IdentsAbsSyn;
import scanner.token.IToken;
import scanner.tokenList.ITokenList;
import java.util.List;
/**
 * Created by tobi on 17.12.16.
 */
public class IdentsConcSyn extends AbstractConcSyn implements IConcSyn {
    public IdentsConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    private IToken token;
    @Override
    public IAbsSyn toAbsSyn() throws ContextError {
        //Für jedes Nichtterminalsymbol (unten mit ParseNext deklariert) wird eine Liste mit den dazugehörigen Elementen dem Abstrakten Syntaxbaum übergeben.
        List<IAbsSyn> RepeatingOptionalIdentsConcSyn = super.getListByType(RepeatingOptionalIdentsConcSyn.class);

        return new IdentsAbsSyn(token, RepeatingOptionalIdentsConcSyn);
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.SKIP) {
            consume();
            parseNext(new RepeatingOptionalIdentsConcSyn(getTokenList(), getCounter()));
        } else {
            throwGrammarError();
        }
    }
}
