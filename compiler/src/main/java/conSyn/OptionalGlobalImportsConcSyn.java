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
    private GlobalImportConcSyn globalImportConcSyn;
    private RepeatingOptionalGlobalImportsConcSyn repeatingOptionalGlobalImportsConcSyn;

    public OptionalGlobalImportsConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public OptionalGlobalImportsAbsSyn toAbsSyn()throws ContextError {
        return new OptionalGlobalImportsAbsSyn(globalImportConcSyn.toAbsSyn(), repeatingOptionalGlobalImportsConcSyn.toAbsSyn());
    }

    /**
     * TODO: split up 28.12.2016
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.DO
                || getTokenList().getCurrent().getTerminal() == Terminal.LOCAL) {

        } else if (getTokenList().getCurrent().getTerminal() == Terminal.GLOBAL) {
            consume();

            globalImportConcSyn = new GlobalImportConcSyn(getTokenList(), getCounter());
            parseNext(globalImportConcSyn);

            repeatingOptionalGlobalImportsConcSyn = new RepeatingOptionalGlobalImportsConcSyn(getTokenList(), getCounter());
            parseNext(repeatingOptionalGlobalImportsConcSyn);
        } else {
            throwGrammarError();
        }
    }
}