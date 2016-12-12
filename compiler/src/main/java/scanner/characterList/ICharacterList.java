package scanner.characterList;

/**
 * 
 * @author Roman
 * taken from slides
 */
public interface ICharacterList extends Cloneable {
	/**
	 * for reading the token list by the parser (2)
	 * @return
	 */
	Character next();

	Character previous();

	boolean isCurrentNumeric();

	boolean isCurrentWhiteSpace();

	boolean isText();

	Character getCurrentCharacter();
}
