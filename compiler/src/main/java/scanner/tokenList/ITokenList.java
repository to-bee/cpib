package scanner.tokenList;

import scanner.token.IToken;

import java.util.List;
import java.util.stream.Stream;

/**
 * 
 * @author Roman
 * taken from slides
 */
public interface ITokenList extends Cloneable {
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

	Stream<IToken> stream();

	ITokenList clone();

    List<IToken> toList();

	int size();
}
