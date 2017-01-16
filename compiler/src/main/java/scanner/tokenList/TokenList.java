package scanner.tokenList;

import java.util.*;
import java.util.stream.Stream;

import scanner.token.IToken;

/**
 * Pretty pointless for now, since its just wrapping the linkedlist. Take out later if no need for this arises.
 *
 * @author Roman
 */
public class TokenList implements ITokenList {

    private LinkedList<IToken> tokenList = new LinkedList<>();
    private int position;

    public TokenList(LinkedList<IToken> tokenList) {
        this.tokenList = tokenList;
    }

    public TokenList() {
    }

    public void reset() {
        //Not the fastest, maybe think of a better solution
        tokenList.clear();
    }

    public void add(IToken token) {
        tokenList.add(token);
    }

    private boolean isOutOfBounce() {
        return this.position < 0 || this.position >= this.tokenList.size();
    }

    /**
     * returns first Token in list or NoSuchElementException - if this list is empty
     * Also removeLast() possible whichever we will need. Will destroy the tokenlist eventually.
     */
    public IToken nextToken() {
        position++;
        return getCurrent();
    }

    @Override
    public IToken getCurrent() {
        return this.isOutOfBounce() ? null : this.tokenList.get(position);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        Iterator<IToken> iterator = tokenList.iterator();
        while(iterator.hasNext())
        {
            sb.append(iterator.next().toString());
            if(iterator.hasNext()) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }

    @Override
    public Stream<IToken> stream() {
        return this.tokenList.stream();
    }

    /**
     * @return
     */
    @Override
    public TokenList clone() {
        return new TokenList(tokenList);
    }

    public List<IToken> toList() {
        return new ArrayList<IToken>(this.tokenList);
    }

    @Override
    public int size() {
        return this.tokenList.size();
    }
}
