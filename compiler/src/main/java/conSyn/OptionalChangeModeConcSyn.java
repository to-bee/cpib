package conSyn;

import absSyn.OptionalChangeModeAbsSyn;
import absSyn.ProgramParameterListAbsSyn;
import scanner.datatypes.TerminalType;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

/**
 * Created by tobi on 17.12.16.
 */
public class OptionalChangeModeConcSyn extends AbstractConcSyn implements IConcSyn {
    public OptionalChangeModeConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public OptionalChangeModeAbsSyn toAbsSyn() throws ContextError {
        //besitzt keine Nonterminals.
        return null;
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT) {
            // TODO: save ident
        } else if (getTokenList().getCurrent().getTerminal().getType() == TerminalType.CHANGEMODE) {
            consume();
        } else {
            throwGrammarError();
        }
    }
}
