package scanner.states;

import scanner.Scanner;
import scanner.characterList.ICharacterList;
import scanner.errors.LexicalError;
import scanner.token.IToken;

/**
 * Created by tobi on 12.12.16.
 */
public class WhiteSpaceTokenState extends AbstractTokenState {

    public WhiteSpaceTokenState(Scanner owner) {
        super(owner);
    }

    @Override
    public ITokenState scan(ICharacterList characterList) {
        Character c = characterList.next();
        if(c == null) {
            addTokenFromWord();
            return null;
        }

        if (characterList.isCurrentNumeric()) {
            return new LiteralTokenState(this.getOwner());
        } else if (characterList.isText()) {
            return new IdentTokenState(this.getOwner());
        } else if (characterList.isCurrentWhiteSpace()) {
            return new WhiteSpaceTokenState(this.getOwner());
        } else {
            return new InstructionTokenState(this.getOwner());
        }
    }

    protected IToken getTokenByWord(String word) {
        throw new LexicalError("Whitespace cannot be a token");
    }


}
