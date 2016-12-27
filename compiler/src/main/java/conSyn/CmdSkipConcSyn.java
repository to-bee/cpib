package conSyn;

import absSyn.CmdAbsSyn;
import absSyn.CmdSkipAbsSyn;
import absSyn.IAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.token.IToken;
import scanner.tokenList.ITokenList;

import java.util.List;

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
