package conSyn;

import absSyn.OptionalProgramParamAbsSyn1;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class OptionalProgramParamConcSyn1 extends AbstractConcSyn implements IConcSyn {
    private RepeatingOptionalProgramParametersConcSyn repeatingOptionalProgramParametersConcSyn;
    private OptionalFlowModeConcSyn optionalFlowModeConcSyn;
    private OptionalChangeModeConcSyn optionalChangeModeConcSyn;
    private TypedIdentConcSyn typedIdentConcSyn;

    public OptionalProgramParamConcSyn1(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public OptionalProgramParamAbsSyn1 toAbsSyn() throws ContextError {
        return new OptionalProgramParamAbsSyn1(optionalFlowModeConcSyn.toAbsSyn(), optionalChangeModeConcSyn.toAbsSyn(), typedIdentConcSyn.toAbsSyn(), repeatingOptionalProgramParametersConcSyn.toAbsSyn());
    }

    /**
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        optionalFlowModeConcSyn = new OptionalFlowModeConcSyn(getTokenList(), getCounter());
        parseNext(optionalFlowModeConcSyn);

        optionalChangeModeConcSyn = new OptionalChangeModeConcSyn(getTokenList(), getCounter());
        parseNext(optionalChangeModeConcSyn);

        typedIdentConcSyn = new TypedIdentConcSyn(getTokenList(), getCounter());
        parseNext(typedIdentConcSyn);

        repeatingOptionalProgramParametersConcSyn = new RepeatingOptionalProgramParametersConcSyn(getTokenList(), getCounter());
        parseNext(repeatingOptionalProgramParametersConcSyn);
    }
}
