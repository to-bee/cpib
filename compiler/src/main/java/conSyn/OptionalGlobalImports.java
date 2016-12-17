package conSyn;

import scanner.datatypes.Terminal;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class OptionalGlobalImports extends AbstractConcSyn implements IConcSyn {
    public OptionalGlobalImports(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.DO
                || getTokenList().getCurrent().getTerminal() == Terminal.LOCAL) {

        } else if (getTokenList().getCurrent().getTerminal() == Terminal.GLOBAL) {
            consume();
            parseNext(new GlobalImport(getTokenList(), getCounter()));
            parseNext(new RepeatingOptionalGlobalImports(getTokenList(), getCounter()));
        } else {
            throwGrammarError();
        }
    }
}