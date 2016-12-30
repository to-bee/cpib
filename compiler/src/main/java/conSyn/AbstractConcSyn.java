package conSyn;

import absSyn.IAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
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
    private final int counter;
    private List<IConcSyn> concComponents = new ArrayList<>();
    private List<IToken> tokens = new ArrayList<>();

    public AbstractConcSyn(ITokenList tokenList, int i) {
        this.tokenList = tokenList;
        this.counter = i + 1;
    }

    /**
     * Loads the next token
     *
     * @throws GrammarError
     */
    public void consume() throws GrammarError {
        IToken token = tokenList.nextToken();
        tokens.add(token);
    }

    private String getTabs(int tabsCount) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tabsCount * 2; i++) {
            sb.append("  ");
        }
        return sb.toString();
    }

    public int getCounter() {
        return counter;
    }

    public void parseNext(IConcSyn concSyn) throws GrammarError {
        this.concComponents.add(concSyn);
        concSyn.parse();
    }

    /**
     * output position from scanner.Scanner
     *
     * @throws GrammarError
     */
    protected void throwGrammarError() throws GrammarError {
        throw new GrammarError(String.format("%s: Could not parse grammar", getTokenList().getCurrent().toString()));
    }

    public ITokenList getTokenList() {
        return tokenList;
    }

    public abstract IAbsSyn toAbsSyn() throws ContextError;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String tabs = getTabs(getCounter());
        sb.append(String.format("%s%s: %s\n", tabs, getClass().getName(), tokens.toString()));
        for(IConcSyn child : concComponents) {
            sb.append(child.toString());
        }
        return sb.toString();
    }
}
