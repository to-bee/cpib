package conSyn;

import scanner.datatypes.Terminal;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class ProgramParameterList extends AbstractConcSyn implements IConcSyn {
    public ProgramParameterList(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.LPAREN) {
            consume();
            parseNext(new OptionalProgramParam(getTokenList(), getCounter()));
        } else {
            throwGrammarError();
        }
    }
}
