package conSyn;

import model.token.IToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tobi on 01.11.16.
 */
public class ConcSyn implements IConcSyn {
    private List<IToken> tokenList = new ArrayList();

    public ConcSyn() {
    }

    public boolean add(IToken token) {
        return this.tokenList.add(token);
    }
}
