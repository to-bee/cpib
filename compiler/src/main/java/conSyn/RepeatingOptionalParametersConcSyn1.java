package conSyn;

import absSyn.RepeatingOptionalParametersAbsSyn1;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class RepeatingOptionalParametersConcSyn1 extends AbstractConcSyn implements IConcSyn {
    private ParameterConcSyn parameterConcSyn;
    private RepeatingOptionalParametersConcSyn1 repeatingOptionalParametersConcSyn;

    public RepeatingOptionalParametersConcSyn1(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public RepeatingOptionalParametersAbsSyn1 toAbsSyn() throws ContextError {
        return new RepeatingOptionalParametersAbsSyn1(parameterConcSyn.toAbsSyn(), repeatingOptionalParametersConcSyn.toAbsSyn());
    }

    /**
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        consume();
        parameterConcSyn = new ParameterConcSyn(getTokenList(), getCounter());
        parseNext(parameterConcSyn);
        repeatingOptionalParametersConcSyn = new RepeatingOptionalParametersConcSyn1(getTokenList(), getCounter());
        parseNext(repeatingOptionalParametersConcSyn);
    }
}
