package scanner.token;


import scanner.datatypes.Terminal;

/**
 * To add the lexemes to the tokenlist
 * @author Roman
 *
 */
public interface IToken {
	
	String toString();
	
	Terminal getTerminal();

}
