package scanner.token;


import context.Type;
import scanner.datatypes.Terminal;

/**
 * To addVar the lexemes to the tokenlist
 * @author Roman
 *
 */
public interface IToken {
	
	String toString();
	
	Terminal getTerminal();
}
