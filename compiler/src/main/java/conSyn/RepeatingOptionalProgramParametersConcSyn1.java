package conSyn;

import absSyn.RepeatingOptionalProgramParametersAbsSyn1;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class RepeatingOptionalProgramParametersConcSyn1 extends AbstractConcSyn implements IConcSyn {
    private OptionalFlowModeConcSyn optionalFlowModeConcSyn;
    private OptionalChangeModeConcSyn optionalChangeModeConcSyn;
    private TypedIdentConcSyn typedIdentConcSyn;
    private RepeatingOptionalParametersConcSyn repeatingOptionalParametersConcSyn;

    public RepeatingOptionalProgramParametersConcSyn1(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public RepeatingOptionalProgramParametersAbsSyn1 toAbsSyn() throws ContextError {
        return new RepeatingOptionalProgramParametersAbsSyn1(
                optionalFlowModeConcSyn.toAbsSyn(),
                optionalChangeModeConcSyn.toAbsSyn(),
                typedIdentConcSyn.toAbsSyn(),
                repeatingOptionalParametersConcSyn.toAbsSyn());
    }

    /**
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        consume();
        optionalFlowModeConcSyn = new OptionalFlowModeConcSyn(getTokenList(), getCounter());
        parseNext(optionalFlowModeConcSyn);
        optionalChangeModeConcSyn = new OptionalChangeModeConcSyn(getTokenList(), getCounter());
        parseNext(optionalChangeModeConcSyn);
        typedIdentConcSyn = new TypedIdentConcSyn(getTokenList(), getCounter());
        parseNext(typedIdentConcSyn);
        repeatingOptionalParametersConcSyn = new RepeatingOptionalParametersConcSyn(getTokenList(), getCounter());
        parseNext(repeatingOptionalParametersConcSyn);
    }
}
