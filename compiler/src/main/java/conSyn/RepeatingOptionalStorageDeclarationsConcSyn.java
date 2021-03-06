package conSyn;

import absSyn.RepeatingOptionalStorageDeclarationsAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
/**
 * Created by tobi on 17.12.16.
 */
public class RepeatingOptionalStorageDeclarationsConcSyn extends AbstractConcSyn implements IConcSyn {
    private IConcSyn subType;

    public RepeatingOptionalStorageDeclarationsConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public RepeatingOptionalStorageDeclarationsAbsSyn toAbsSyn()throws ContextError {
        return new RepeatingOptionalStorageDeclarationsAbsSyn(subType.toAbsSyn());
    }

    /**
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.DO) {
            subType = new EmptyConcSyn(getTokenList(), getCounter());
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.SEMICOLON) {
            subType = new RepeatingOptionalStorageDeclarationsConcSyn1(getTokenList(), getCounter());
        } else {
            throwGrammarError();
        }
        if (subType != null) {
            parseNext(subType);
        }else {
            throwGrammarError();
        }
    }
}
