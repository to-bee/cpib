package conSyn;

import absSyn.IAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.token.IToken;
import scanner.tokenList.ITokenList;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Created by tobi on 01.11.16.
 */
public abstract class AbstractConcSyn implements IConcSyn {
    private final ITokenList tokenList;
    private final int counter;
    private List<IConcSyn> childs = new ArrayList<>();

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

        if (token != null) {
            String tabs = getTabs(getCounter());
            System.out.printf("%s%s: %s\n", tabs, getClass().getName(), token.toString());
        }

//        tokens.add(token);
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

    public ITokenList getTokenList() {
        return tokenList;
    }

    private ITokenList getPossibleFollowingToken(Terminal terminal, Terminal followingTerminal) throws GrammarError {
        throw new GrammarError(String.format("%s cannot follow to %s", terminal, followingTerminal));
    }

    public abstract IAbsSyn toAbsSyn() throws ContextError;

    protected List<IAbsSyn> getListByType(Class type) {
        List<IAbsSyn> list = null;
        try {
            list = this.getChilds().stream().filter(c -> c.getClass() == type).map(c -> {
                try {
                    return c.toAbsSyn();
                } catch (ContextError contextError) {
                    contextError.printStackTrace();
                    return null;
                }
            }).collect(Collectors.toList());
        } catch (NoSuchElementException e) {
        }

        return list;
    }

    protected IAbsSyn getOneByType(Class type) throws ContextError {
        return this.getChilds().stream().filter(c -> c.getClass() == type).findFirst().get().toAbsSyn();
    }

    public List<IConcSyn> getChilds() {
        return childs;
    }


//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        for(int i = 0; i<tabs*4; i++) {
//            sb.append(" ");
//        }
//        for(IConcSyn child : childs) {
//            sb.append(child.toString());
//            sb.append("\n");
//        }
//        return sb.toString();
//    }
}
