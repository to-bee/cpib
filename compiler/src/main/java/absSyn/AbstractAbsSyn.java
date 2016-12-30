package absSyn;

import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
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

    protected AbstractAbsSyn() {
    }

    protected String getTabs(int tabsCount) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tabsCount * 2; i++) {
            sb.append("  ");
        }
        return sb.toString();
    }

    public String toString(int counter) {
        return "";
    }
}
