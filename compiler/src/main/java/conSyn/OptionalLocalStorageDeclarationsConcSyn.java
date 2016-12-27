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
    private StorageDeclarationConcSyn storageDeclarationConcSyn;
    private RepeatingOptionalStorageDeclarationsConcSyn repeatingOptionalStorageDeclarationsConcSyn;

    public OptionalLocalStorageDeclarationsConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    private IToken token;
    @Override
    public OptionalLocalStorageDeclarationsAbsSyn toAbsSyn() throws ContextError {
        return new OptionalLocalStorageDeclarationsAbsSyn(storageDeclarationConcSyn.toAbsSyn(), repeatingOptionalStorageDeclarationsConcSyn.toAbsSyn());
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.DO) {

        } else if (getTokenList().getCurrent().getTerminal() == Terminal.LOCAL) {
            consume();

            storageDeclarationConcSyn = new StorageDeclarationConcSyn(getTokenList(), getCounter());
            parseNext(storageDeclarationConcSyn);

            repeatingOptionalStorageDeclarationsConcSyn = new RepeatingOptionalStorageDeclarationsConcSyn(getTokenList(), getCounter());
            parseNext(repeatingOptionalStorageDeclarationsConcSyn);
        }
    }
}
