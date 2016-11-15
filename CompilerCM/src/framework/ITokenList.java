package framework;

/**
 * Source: Slides02_ScanningV1, p. 9
 * 
 * @author Simon
 *
 */
public interface ITokenList {
	// for constructing the token list by the scanner
	void add(IToken iToken);

	// for reading the token list by the parser (1)
	void reset();

	// for reading the token list by the parser (2)
	IToken nextToken();

	// for displaying the token list
	String toString();
}
