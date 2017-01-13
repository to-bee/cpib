package conSyn;

import absSyn.OptionalGlobalImportsAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class OptionalGlobalImportsConcSyn extends AbstractConcSyn implements IConcSyn {
    private IConcSyn subType;
    private Terminal terminal;

    public OptionalGlobalImportsConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public OptionalGlobalImportsAbsSyn toAbsSyn() throws ContextError {
        return new OptionalGlobalImportsAbsSyn(subType.toAbsSyn(), terminal);
    }

    /**
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        this.terminal = getTokenList().getCurrent().getTerminal();
        if (this.terminal == Terminal.DO
                || this.terminal == Terminal.LOCAL) {
            subType = new EmptyConcSyn(getTokenList(), getCounter());
        } else if (this.terminal == Terminal.GLOBAL) {
            subType = new OptionalGlobalImportsConcSyn1(getTokenList(), getCounter());
        } else {
            throwGrammarError();
        }
        if (subType != null) {
            parseNext(subType);
        } else {
            throwGrammarError();
        }
    }
}
