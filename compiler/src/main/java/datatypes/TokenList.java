package datatypes;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import interfaces.IToken;
import interfaces.ITokenList;
import tokens.BaseToken;

/**
 * Pretty pointless for now, since its just wrapping the linkedlist. Take out later if no need for this arises.
 * @author Roman
 *
 */
public class TokenList implements ITokenList {
	
	private LinkedList<IToken> tokenList = new LinkedList<IToken>();
	
	public void reset() {
		//Not the fastest, maybe think of a better solution
		tokenList.clear();
	}

	public void add(IToken token) {
		tokenList.add(token);
	}

	/**
	 * returns first Token in list or NoSuchElementException - if this list is empty
	 */
	public IToken nextToken() {
		//Also removeLast() possible whichever we will need. Will distroy the tokenlist eventually.
		return tokenList.removeFirst();
	}
	
	public String toString(){
		return null;
	}

}
