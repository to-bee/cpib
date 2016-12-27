package conSyn;

import absSyn.IAbsSyn;
import absSyn.ProgramParameterListAbsSyn;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

import absSyn.OptionalLocalStorageDeclarationsAbsSyn;
import scanner.token.IToken;

import java.util.List;
/**
 * Created by tobi on 17.12.16.
 */
public class OptionalLocalStorageDeclarationsConcSyn extends AbstractConcSyn implements IConcSyn {
    public OptionalLocalStorageDeclarationsConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    private IToken token;
    @Override
    public OptionalLocalStorageDeclarationsAbsSyn toAbsSyn() throws ContextError {
        //Für jedes Nichtterminalsymbol (unten mit ParseNext deklariert) wird eine Liste mit den dazugehörigen Elementen dem Abstrakten Syntaxbaum übergeben.
        List<IAbsSyn> StorageDeclarationConcSyn = super.getListByType(StorageDeclarationConcSyn.class);
        List<IAbsSyn> RepeatingOptionalStorageDeclarationsConcSyn = super.getListByType(RepeatingOptionalStorageDeclarationsConcSyn.class);

        return new OptionalLocalStorageDeclarationsAbsSyn(token, StorageDeclarationConcSyn, RepeatingOptionalStorageDeclarationsConcSyn);
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.DO) {

        } else if (getTokenList().getCurrent().getTerminal() == Terminal.LOCAL) {
            consume();
            parseNext(new StorageDeclarationConcSyn(getTokenList(), getCounter()));
            parseNext(new RepeatingOptionalStorageDeclarationsConcSyn(getTokenList(), getCounter()));
        }
    }
}
