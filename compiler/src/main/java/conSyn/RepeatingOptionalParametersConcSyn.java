package conSyn;

import absSyn.RepeatingOptionalParametersAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.token.IToken;
import scanner.tokenList.ITokenList;
/**
 * Created by tobi on 17.12.16.
 */
public class RepeatingOptionalParametersConcSyn extends AbstractConcSyn implements IConcSyn {
    private ParameterConcSyn parameterConcSyn;
    private RepeatingOptionalParametersConcSyn repeatingOptionalParametersConcSyn;
    private IToken token;

    public RepeatingOptionalParametersConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public RepeatingOptionalParametersAbsSyn toAbsSyn() throws ContextError {
        return new RepeatingOptionalParametersAbsSyn(parameterConcSyn.toAbsSyn(), repeatingOptionalParametersConcSyn.toAbsSyn());
    }

    /**
     * TODO
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.RPAREN) {

        } else if (getTokenList().getCurrent().getTerminal() == Terminal.COMMA) {
            consume();

            parameterConcSyn = new ParameterConcSyn(getTokenList(), getCounter());
            parseNext(parameterConcSyn);

            repeatingOptionalParametersConcSyn = new RepeatingOptionalParametersConcSyn(getTokenList(), getCounter());
            parseNext(repeatingOptionalParametersConcSyn);
        } else {
            throwGrammarError();
        }
    }
}
