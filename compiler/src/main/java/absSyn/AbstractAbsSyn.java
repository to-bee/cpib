package absSyn;

import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.errors.GrammarError;
import scanner.token.IToken;
import scanner.token.Ident;
import scanner.tokenList.ITokenList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tobi on 01.11.16.
 */
public abstract class AbstractAbsSyn implements IAbsSyn {
    public AbstractAbsSyn(IToken token) {
        this.token = token;
    }

    public IToken getToken() {
        return token;
    }

    private IToken token;
}