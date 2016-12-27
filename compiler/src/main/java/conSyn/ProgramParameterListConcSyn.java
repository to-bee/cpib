package conSyn;

import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

import absSyn.ProgramParameterListAbsSyn;

/**
 * Created by tobi on 17.12.16.
 */
public class ProgramParameterListConcSyn extends AbstractConcSyn implements IConcSyn {
    private OptionalProgramParamConcSyn optionalProgramParamConcSyn;

    public ProgramParameterListConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public ProgramParameterListAbsSyn toAbsSyn() throws ContextError {
        return new ProgramParameterListAbsSyn(optionalProgramParamConcSyn.toAbsSyn());
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.LPAREN) {
            consume();
            optionalProgramParamConcSyn = new OptionalProgramParamConcSyn(getTokenList(), getCounter());
            parseNext(optionalProgramParamConcSyn);
        } else {
            throwGrammarError();
        }
    }
}
