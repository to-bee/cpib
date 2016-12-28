package conSyn;

import absSyn.RepeatingOptionalParametersAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
/**
 * Created by tobi on 17.12.16.
 */
public class RepeatingOptionalParametersConcSyn extends AbstractConcSyn implements IConcSyn {
    private IConcSyn subType;

    public RepeatingOptionalParametersConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public RepeatingOptionalParametersAbsSyn toAbsSyn() throws ContextError {
        return new RepeatingOptionalParametersAbsSyn(subType.toAbsSyn());
    }

    /**
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.RPAREN) {
            subType = new EmptyConcSyn(getTokenList(), getCounter());
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.COMMA) {
            subType = new RepeatingOptionalParametersConcSyn1(getTokenList(), getCounter());
        } else {
            throwGrammarError();
        }
        if (subType != null) {
            parseNext(subType);
        }else {
            throwGrammarError();
        }
    }
}
