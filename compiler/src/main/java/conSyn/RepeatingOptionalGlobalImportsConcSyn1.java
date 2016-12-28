package conSyn;

import absSyn.RepeatingOptionalGlobalImportsAbsSyn1;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class RepeatingOptionalGlobalImportsConcSyn1 extends AbstractConcSyn implements IConcSyn {
    private GlobalImportConcSyn globalImportConcSyn;
    private RepeatingOptionalGlobalImportsConcSyn1 repeatingOptionalGlobalImportsConcSyn;

    public RepeatingOptionalGlobalImportsConcSyn1(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public RepeatingOptionalGlobalImportsAbsSyn1 toAbsSyn() throws ContextError {
        return new RepeatingOptionalGlobalImportsAbsSyn1(globalImportConcSyn.toAbsSyn(), repeatingOptionalGlobalImportsConcSyn.toAbsSyn());
    }

    /**
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        consume();
        globalImportConcSyn = new GlobalImportConcSyn(getTokenList(), getCounter());
        parseNext(globalImportConcSyn);
        repeatingOptionalGlobalImportsConcSyn = new RepeatingOptionalGlobalImportsConcSyn1(getTokenList(), getCounter());
        parseNext(repeatingOptionalGlobalImportsConcSyn);
    }
}