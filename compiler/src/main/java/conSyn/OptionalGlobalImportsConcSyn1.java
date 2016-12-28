package conSyn;

import absSyn.OptionalGlobalImportsAbsSyn1;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class OptionalGlobalImportsConcSyn1 extends AbstractConcSyn implements IConcSyn {
    private GlobalImportConcSyn globalImportConcSyn;
    private RepeatingOptionalGlobalImportsConcSyn repeatingOptionalGlobalImportsConcSyn;
    public OptionalGlobalImportsConcSyn1(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public OptionalGlobalImportsAbsSyn1 toAbsSyn()throws ContextError {
        return new OptionalGlobalImportsAbsSyn1(globalImportConcSyn.toAbsSyn(), repeatingOptionalGlobalImportsConcSyn.toAbsSyn());
    }

    /**
     *
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        consume();

        globalImportConcSyn = new GlobalImportConcSyn(getTokenList(), getCounter());
        parseNext(globalImportConcSyn);

        repeatingOptionalGlobalImportsConcSyn = new RepeatingOptionalGlobalImportsConcSyn(getTokenList(), getCounter());
        parseNext(repeatingOptionalGlobalImportsConcSyn);
    }
}