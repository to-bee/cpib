package conSyn;

import absSyn.OptionalParametersAbsSyn2;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class OptionalParametersConcSyn2 extends AbstractConcSyn implements IConcSyn {
    private ParameterConcSyn parameterConcSyn;
    private RepeatingOptionalParametersConcSyn repeatingOptionalParametersConcSyn;
    private IConcSyn subType;

    public OptionalParametersConcSyn2(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public OptionalParametersAbsSyn2 toAbsSyn() throws ContextError {
        return new OptionalParametersAbsSyn2(parameterConcSyn.toAbsSyn(), repeatingOptionalParametersConcSyn.toAbsSyn());
    }

    /**
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        parameterConcSyn = new ParameterConcSyn(getTokenList(), getCounter());
        parseNext(parameterConcSyn);
        repeatingOptionalParametersConcSyn = new RepeatingOptionalParametersConcSyn(getTokenList(), getCounter());
        parseNext(repeatingOptionalParametersConcSyn);
    }
}
