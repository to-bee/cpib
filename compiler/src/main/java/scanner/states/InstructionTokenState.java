package scanner.states;

import scanner.Scanner;
import scanner.characterList.ICharacterList;
import scanner.datatypes.TerminalType;
import scanner.datatypes.Terminal;
import scanner.errors.LexicalError;
import scanner.token.BaseToken;
import scanner.token.IToken;
import scanner.token.Opr;

/**
 * Created by tobi on 12.12.16.
 */
public class InstructionTokenState extends AbstractTokenState {

    public InstructionTokenState(Scanner owner) {
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
            addTokenFromWord();
            return new LiteralTokenState(this.getOwner());
        } else if (characterList.isText()) {
            addTokenFromWord();
            return new IdentTokenState(this.getOwner());
        } else if (characterList.isCurrentWhiteSpace()) {
            addTokenFromWord();
            return new WhiteSpaceTokenState(this.getOwner());
        }
        // Special character or similar
        else {
            this.getWord().append(characterList.getCurrentCharacter());
            characterList.next();
            return this;
        }
    }

    protected void addTokenFromWord() {
        boolean tokensAdded = false;
        String word = getWord().toString();
        /**
         * First try to get token from whole word
         */
        if(word.length() > 0) {
            IToken token = getTokenByWord(word);
            if(token != null) {
                getOwner().getTokenList().add(token);
                tokensAdded = true;
            }
            /**
             * In case that word is not a token
             * Example: word==()
             * () is not a token, but ( is one and ) is another one
             */
            else if(word.length() > 1) {
                for(char c : word.toCharArray()) {
                    token = getTokenByWord(String.valueOf(c));
                    if(token != null) {
                        getOwner().getTokenList().add(token);
                        tokensAdded = true;
                    }
                }
            }
        }

        if(!tokensAdded) {
            throw new LexicalError("Instruction token could not be fetched");
        }
    }

    protected IToken getTokenByWord(String word) {
        IToken token = null;
        Terminal terminal;
        TerminalType operator;
        // Operator
        if ((operator = TerminalType.getOperatorFromString(word)) != null) {
            token = new Opr(operator);
//            if (operator.getOperatorTypes().stream().anyMatch(TerminalType.getAllOperatorTerminals()::contains)) {
//                token = new Opr(operator);
//            } else {
//                token = new BaseToken(Terminal.UNDEFINED);
//            }
        }
        else if ((terminal = Terminal.getTerminalFromString(word)) != Terminal.UNDEFINED) {
            token = new BaseToken(terminal);
        }

        return token;
    }


}
