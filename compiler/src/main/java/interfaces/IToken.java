package interfaces;

import datatypes.Terminals;

/**
 * To add the lexemes to the tokenlist
 * @author Roman
 *
 */
public interface IToken {
	
	String toString();
	
	Terminals getTerminal();

}
