package conSyn;

import absSyn.OptionalParametersAbsSyn1;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.token.Ident;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class OptionalParametersConcSyn1 extends AbstractConcSyn implements IConcSyn {
    private ParameterConcSyn parameterConcSyn;
    private RepeatingOptionalParametersConcSyn repeatingOptionalParametersConcSyn;
    private Ident ident;

    public OptionalParametersConcSyn1(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public OptionalParametersAbsSyn1 toAbsSyn() throws ContextError {
        return new OptionalParametersAbsSyn1(
                ident,
                parameterConcSyn.toAbsSyn(),
                repeatingOptionalParametersConcSyn.toAbsSyn());
    }

    /**
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        ident = (Ident) this.getTokenList().getCurrent();
        parameterConcSyn = new ParameterConcSyn(getTokenList(), getCounter());
        parseNext(parameterConcSyn);
        repeatingOptionalParametersConcSyn = new RepeatingOptionalParametersConcSyn(getTokenList(), getCounter());
        parseNext(repeatingOptionalParametersConcSyn);
    }
}
