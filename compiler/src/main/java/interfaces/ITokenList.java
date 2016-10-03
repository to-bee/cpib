package interfaces;

import java.util.LinkedList;

/**
 * 
 * @author Roman
 * taken from slides
 */
public interface ITokenList {
	/**
	 * for constructing the token list by the scanner
	 */
	void add(IToken token);

	/**
	 * for reading the token list by the parser (1)
	 */
	void reset();

	/**
	 * for reading the token list by the parser (2)
	 * @return
	 */
	IToken nextToken();

	/**
	 * for displaying the token list
	 * @return
	 */
	String toString();
}
