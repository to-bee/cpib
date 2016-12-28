package conSyn;

import absSyn.CmdSkipAbsSyn;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class CmdSkipConcSyn extends AbstractConcSyn implements IConcSyn {
    public CmdSkipConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public CmdSkipAbsSyn toAbsSyn() throws ContextError {
        return new CmdSkipAbsSyn();
    }

    @Override
    public void parse() throws GrammarError {
        consume();
    }
}
