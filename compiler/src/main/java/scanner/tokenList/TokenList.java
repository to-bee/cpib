package scanner.tokenList;

import java.util.*;
import java.util.stream.Stream;

import scanner.token.IToken;

/**
 * Pretty pointless for now, since its just wrapping the linkedlist. Take out later if no need for this arises.
 * @author Roman
 *
 */
public class TokenList implements ITokenList {
	
	private LinkedList<IToken> tokenList = new LinkedList<IToken>();

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

	/**
	 * returns first Token in list or NoSuchElementException - if this list is empty
	 * 	Also removeLast() possible whichever we will need. Will destroy the tokenlist eventually.
	 */
	public IToken nextToken() {
		return tokenList.size() > 0 ? tokenList.removeFirst() : null;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		Iterator<IToken> tokenIt = tokenList.iterator();
		for(IToken token : tokenList) {
			sb.append(String.format("%s", token.toString()));
		}

		return sb.toString();
	}

	@Override
	public Stream<IToken> stream() {
		return this.tokenList.stream();
	}

	/**
	 * TODO:
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
