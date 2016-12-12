package scanner.characterList;

/**
 * Pretty pointless for now, since its just wrapping the linkedlist. Take out later if no need for this arises.
 *
 * @author Roman
 */
public class CharacterList implements ICharacterList {

    private char[] chars;
    private int position;

    public CharacterList(String str) {
        this.chars = str.toCharArray();
        this.position = 0;
    }

    public Character next() {
        position++;
        return this.isOutOfBounce() ? null : chars[position];
    }

    @Override
    public Character previous() {
        --position;
        return this.isOutOfBounce() ? null : chars[position];
    }

    public boolean isCurrentNumeric() {
        return Character.isDigit(this.getCurrentCharacter());
    }

    private boolean isOutOfBounce() {
        return this.position < 0 || this.position >= this.chars.length;
    }

    public boolean isCurrentWhiteSpace() {
        return this.chars[position] == ' ' || this.chars[position] == '\n';
    }

    @Override
    public boolean isText() {
        return Character.isLetter(this.chars[position]);
    }

    @Override
    public Character getCurrentCharacter() {
        return isOutOfBounce() ? null : this.chars[position];
    }
}
