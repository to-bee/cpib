package conSyn;

import absSyn.IAbsSyn;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

/**
 * Created by tobi on 17.12.16.
 */
public class RepeatingOptionalProgramParametersConcSyn extends AbstractConcSyn implements IConcSyn {
    public RepeatingOptionalProgramParametersConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public IAbsSyn toAbsSyn() {
        //TODO: implement
        return null;
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.RPAREN) {
            consume();
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.COMMA) {
            consume();
            parseNext(new OptionalFlowModeConcSyn(getTokenList(), getCounter()));;
            parseNext(new OptionalChangeModeConcSyn(getTokenList(), getCounter()));
            parseNext(new TypedIdentConcSyn(getTokenList(), getCounter()));
            parseNext(new RepeatingOptionalParametersConcSyn(getTokenList(), getCounter()));
        } else {
            throwGrammarError();
        }
    }
}
