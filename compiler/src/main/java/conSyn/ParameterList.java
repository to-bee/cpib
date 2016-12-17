package conSyn;

import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

/**
 * Created by tobi on 17.12.16.
 */
public class ParameterList extends AbstractConcSyn implements IConcSyn {
    public ParameterList(ITokenList tokenList) {
        super(tokenList);
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.LPAREN) {
            consume();
            parseNext(new OptionalParameters(getTokenList()));;
            if (getTokenList().getCurrent().getTerminal() == Terminal.RPAREN) {
                consume();
            } else {
                throwGrammarError();
            }
        } else {
            throwGrammarError();
        }
    }
}
