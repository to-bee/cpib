package conSyn;

import absSyn.RepeatingOptionalCmdsAbsSyn;
import absSyn.RepeatingOptionalCmdsAbsSyn1;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class RepeatingOptionalCmdsConcSyn1 extends AbstractConcSyn implements IConcSyn {
    private RepeatingOptionalCmdsConcSyn repeatingOptionalCmdsConcSyn;
    private CmdConcSyn cmdConcSyn;

    public RepeatingOptionalCmdsConcSyn1(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public RepeatingOptionalCmdsAbsSyn1 toAbsSyn() throws ContextError {
        return new RepeatingOptionalCmdsAbsSyn1(cmdConcSyn.toAbsSyn(), repeatingOptionalCmdsConcSyn.toAbsSyn());
    }

    /**
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
            consume();
            cmdConcSyn = new CmdConcSyn(getTokenList(), getCounter());
            parseNext(cmdConcSyn);
            repeatingOptionalCmdsConcSyn = new RepeatingOptionalCmdsConcSyn(getTokenList(), getCounter());
            parseNext(repeatingOptionalCmdsConcSyn);
    }
}
