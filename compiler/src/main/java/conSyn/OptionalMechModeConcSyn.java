package conSyn;

import absSyn.OptionalMechModeAbsSyn;
import absSyn.ProgramParameterListAbsSyn;
import scanner.datatypes.TerminalType;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

/**
 * Created by tobi on 17.12.16.
 */
public class OptionalMechModeConcSyn extends AbstractConcSyn implements IConcSyn {
    public OptionalMechModeConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public OptionalMechModeAbsSyn toAbsSyn() throws ContextError {
        return new OptionalMechModeAbsSyn();
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.CHANGEMODE) {

        } else if (getTokenList().getCurrent().getTerminal().getType() == TerminalType.MECHMODE) {
            consume();
        } else{
            throwGrammarError();
        }
    }
}
