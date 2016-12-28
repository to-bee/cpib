package conSyn;

import absSyn.OptionalGlobalDeclarationsAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
/**
 * Created by tobi on 17.12.16.
 */
public class OptionalGlobalDeclarationsConcSyn extends AbstractConcSyn implements IConcSyn {
    private IConcSyn subType;

    public OptionalGlobalDeclarationsConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public OptionalGlobalDeclarationsAbsSyn toAbsSyn()throws ContextError {
        return new OptionalGlobalDeclarationsAbsSyn(subType.toAbsSyn());
    }

    /**
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.DO) {
            subType = new EmptyConsumeConcSyn(getTokenList(), getCounter());
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.GLOBAL) {
            subType = new OptionalGlobalDeclarationsConcSyn1(getTokenList(), getCounter());
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
