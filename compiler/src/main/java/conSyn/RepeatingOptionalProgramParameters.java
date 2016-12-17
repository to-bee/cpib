package conSyn;

import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

/**
 * Created by tobi on 17.12.16.
 */
public class RepeatingOptionalProgramParameters extends AbstractConcSyn implements IConcSyn {
    public RepeatingOptionalProgramParameters(ITokenList tokenList) {
        super(tokenList);
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.RPAREN) {
            consume();
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.COMMA) {
            consume();
            parseNext(new OptionalFlowMode(getTokenList()));;
            parseNext(new OptionalChangeMode(getTokenList()));
            parseNext(new TypedIdent(getTokenList()));
            parseNext(new RepeatingOptionalParameters(getTokenList()));
        } else {
            throwGrammarError();
        }
    }
}
