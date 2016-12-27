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
    private GlobalImportConcSyn globalImportConcSyn;
    private RepeatingOptionalGlobalImportsConcSyn repeatingOptionalGlobalImportsConcSyn;

    public RepeatingOptionalGlobalImportsConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public RepeatingOptionalGlobalImportsAbsSyn toAbsSyn() throws ContextError {
        return new RepeatingOptionalGlobalImportsAbsSyn(globalImportConcSyn.toAbsSyn(), repeatingOptionalGlobalImportsConcSyn.toAbsSyn());
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.DO) {

        } else if (getTokenList().getCurrent().getTerminal() == Terminal.LOCAL) {

        } else if (getTokenList().getCurrent().getTerminal() == Terminal.COMMA) {
            consume();

            globalImportConcSyn = new GlobalImportConcSyn(getTokenList(), getCounter());
            parseNext(globalImportConcSyn);

            repeatingOptionalGlobalImportsConcSyn = new RepeatingOptionalGlobalImportsConcSyn(getTokenList(), getCounter());
            parseNext(repeatingOptionalGlobalImportsConcSyn);
        }
    }
}