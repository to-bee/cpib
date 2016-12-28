package conSyn;

import absSyn.OptionalLocalStorageDeclarationsAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class OptionalLocalStorageDeclarationsConcSyn extends AbstractConcSyn implements IConcSyn {
    private IConcSyn subType;

    public OptionalLocalStorageDeclarationsConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public OptionalLocalStorageDeclarationsAbsSyn toAbsSyn() throws ContextError {
        return new OptionalLocalStorageDeclarationsAbsSyn(subType.toAbsSyn());
    }

    /**
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.DO) {
            subType = new EmptyConcSyn(getTokenList(), getCounter());
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.LOCAL) {
            subType = new OptionalLocalStorageDeclarationsConcSyn1(getTokenList(), getCounter());
        } else {
            //maybe wrong, added exception 28.12.2016 by Yves
            throwGrammarError();
        }
        if (subType != null) {
            parseNext(subType);
        } else {
            throwGrammarError();
        }
    }
}
