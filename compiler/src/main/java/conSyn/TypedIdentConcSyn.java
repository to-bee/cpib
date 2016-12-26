package conSyn;

import absSyn.IAbsSyn;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

import absSyn.TypedIdentAbsSyn;
import scanner.token.IToken;
import scanner.tokenList.ITokenList;
import java.util.List;
/**
 * Created by tobi on 17.12.16.
 */
public class TypedIdentConcSyn extends AbstractConcSyn implements IConcSyn {
    public TypedIdentConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    private IToken token;
    @Override
    public IAbsSyn toAbsSyn()throws ContextError {
        //Für jedes Nichtterminalsymbol (unten mit ParseNext deklariert) wird eine Liste mit den dazugehörigen Elementen dem Abstrakten Syntaxbaum übergeben.
        List<IAbsSyn> TypeDeclarationConcSyn = super.getListByType(TypeDeclarationConcSyn.class);

        return new TypedIdentAbsSyn(token, TypeDeclarationConcSyn);
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT) {
            consume();
            if (getTokenList().getCurrent().getTerminal() == Terminal.COLON) {
                consume();
                parseNext(new TypeDeclarationConcSyn(getTokenList(), getCounter()));
            }
        } else {
            throwGrammarError();
        }
    }
}
