package conSyn;

import absSyn.IAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

import absSyn.OptionalGlobalDeclarationsAbsSyn;
import scanner.token.IToken;
import scanner.tokenList.ITokenList;
import java.util.List;
/**
 * Created by tobi on 17.12.16.
 */
public class OptionalGlobalDeclarationsConcSyn extends AbstractConcSyn implements IConcSyn {
    public OptionalGlobalDeclarationsConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    private IToken token;
    @Override
    public IAbsSyn toAbsSyn()throws ContextError {
        //Für jedes Nichtterminalsymbol (unten mit ParseNext deklariert) wird eine Liste mit den dazugehörigen Elementen dem Abstrakten Syntaxbaum übergeben.
        List<IAbsSyn> DeclarationsConcSyn = super.getListByType(DeclarationsConcSyn.class);
        return new OptionalGlobalDeclarationsAbsSyn(token, DeclarationsConcSyn);
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.DO) {
            consume();
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.GLOBAL) {
            consume();
            parseNext(new DeclarationsConcSyn(getTokenList(), getCounter()));
        } else {
            throwGrammarError();
        }
    }
}
