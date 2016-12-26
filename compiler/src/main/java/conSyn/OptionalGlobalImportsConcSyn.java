package conSyn;

import absSyn.IAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

import absSyn.OptionalGlobalImportsAbsSyn;
import scanner.token.IToken;
import scanner.tokenList.ITokenList;
import java.util.List;
/**
 * Created by tobi on 17.12.16.
 */
public class OptionalGlobalImportsConcSyn extends AbstractConcSyn implements IConcSyn {
    public OptionalGlobalImportsConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    private IToken token;
    @Override
    public IAbsSyn toAbsSyn()throws ContextError {
        //TODO: implement
        //Für jedes Nichtterminalsymbol (unten mit ParseNext deklariert) wird eine Liste mit den dazugehörigen Elementen dem Abstrakten Syntaxbaum übergeben.
        List<IAbsSyn> TODO = super.getListByType(TODO.class);
        IAbsSyn TODO2 = super.getOneByType(TODO2.class);

        return new Schnurzel(token, TODO, TODO2);
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.DO
                || getTokenList().getCurrent().getTerminal() == Terminal.LOCAL) {

        } else if (getTokenList().getCurrent().getTerminal() == Terminal.GLOBAL) {
            consume();
            parseNext(new GlobalImportConcSyn(getTokenList(), getCounter()));
            parseNext(new RepeatingOptionalGlobalImportsConcSyn(getTokenList(), getCounter()));
        } else {
            throwGrammarError();
        }
    }
}