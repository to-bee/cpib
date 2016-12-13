package scanner.states;

import scanner.Scanner;
import scanner.characterList.ICharacterList;
import scanner.datatypes.Terminal;
import scanner.token.BaseToken;
import scanner.token.IToken;
import scanner.token.Ident;

/**
 * Created by tobi on 12.12.16.
 */
public class IdentTokenState extends AbstractTokenState {

    public IdentTokenState(Scanner owner) {
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
            // Token must not begin with a number
            if (isInitial()) {
                return new LiteralTokenState(this.getOwner());
            } else {
                this.getWord().append(characterList.getCurrentCharacter());
                characterList.next();
                return this;
            }
        }
        else if (characterList.isText()) {
            this.getWord().append(characterList.getCurrentCharacter());
            characterList.next();
            return this;
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

    protected IToken getTokenByWord(String word) {
        IToken token;
        Terminal terminal;
        // BaseToken
        if ((terminal = Terminal.getTerminalFromString(word)) != Terminal.UNDEFINED) {
            token = new BaseToken(terminal);
        }
        // Ident
        else {
            token = new Ident(word);
        }
        return token;
    }


}
