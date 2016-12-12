package scanner.states;

import scanner.Scanner;
import scanner.characterList.ICharacterList;
import scanner.errors.LexicalError;
import scanner.token.IToken;
import scanner.token.Literal;

/**
 * Created by tobi on 12.12.16.
 */
public class LiteralTokenState extends AbstractTokenState {

    public LiteralTokenState(Scanner owner) {
        super(owner);
    }

    @Override
    public ITokenState scan(ICharacterList characterList) {
        Character c = characterList.getCurrentCharacter();
        if(c == null) {
            addTokenFromWord();
            return null;
        }

        if (characterList.isCurrentNumeric()) {
            this.getWord().append(characterList.getCurrentCharacter());
            characterList.next();
            return this;
        } else if (characterList.isText()) {
            throw new LexicalError("text must not be followed by a numeric character");
        } else if (characterList.isCurrentWhiteSpace()) {
            addTokenFromWord();
            return new WhiteSpaceTokenState(this.getOwner());
        }
        // Special character or similar
        else {
            addTokenFromWord();
            return new InstructionTokenState(this.getOwner());
        }
    }

    @Override
    protected IToken getTokenByWord(String word) {
        double d = Double.parseDouble(word);
        return new Literal(d);
    }
}
