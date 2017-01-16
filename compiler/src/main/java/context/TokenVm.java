package context;

import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 16.01.17.
 */
public class TokenVm {

    private final ITokenList tokenList;

    public TokenVm(ITokenList tokenList) {
        this.tokenList = tokenList.clone();
    }
}
