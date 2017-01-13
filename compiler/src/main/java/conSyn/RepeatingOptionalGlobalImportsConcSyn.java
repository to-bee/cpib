package conSyn;

import absSyn.RepeatingOptionalGlobalImportsAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class RepeatingOptionalGlobalImportsConcSyn extends AbstractConcSyn implements IConcSyn {
    private IConcSyn subType;
    private Terminal terminal;

    public RepeatingOptionalGlobalImportsConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public RepeatingOptionalGlobalImportsAbsSyn toAbsSyn() throws ContextError {
        return new RepeatingOptionalGlobalImportsAbsSyn(subType.toAbsSyn(), terminal);
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
        } else if (this.terminal == Terminal.COMMA) {
            subType = new RepeatingOptionalGlobalImportsConcSyn1(getTokenList(), getCounter());
        }else {
            //Maybe change, added exception: 28.12.2016, by Yves
            throwGrammarError();
        }
        if (subType != null) {
            parseNext(subType);
        }else {
            throwGrammarError();
        }
    }
}