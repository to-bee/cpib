package conSyn;

import scanner.datatypes.Terminal;
import scanner.errors.GrammarError;
import scanner.token.IToken;
import scanner.tokenList.ITokenList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tobi on 01.11.16.
 */
public abstract class AbstractConcSyn implements IConcSyn {
    private final ITokenList tokenList;
    private List<IConcSyn> childs = new ArrayList<>();

    public AbstractConcSyn(ITokenList tokenList) {
        this.tokenList = tokenList;
    }

    public ITokenList getTokenList() {
        return tokenList;
    }

    /**
     * Loads the next token
     *
     * @throws GrammarError
     */
    public void consume() throws GrammarError {
        tokenList.nextToken();
    }

    public void parseNext(IConcSyn concSyn) throws GrammarError {
        this.childs.add(concSyn);
        concSyn.parse();
    }

    /**
     * TODO: output position from scanner.Scanner
     *
     * @throws GrammarError
     */
    protected void throwGrammarError() throws GrammarError {
        throw new GrammarError(String.format("%s: Could not parse grammar", getTokenList().getCurrent().toString()));
    }

    private ITokenList getPossibleFollowingToken(Terminal terminal, Terminal followingTerminal) throws GrammarError {
        throw new GrammarError(String.format("%s cannot follow to %s", terminal, followingTerminal));
    }
}
