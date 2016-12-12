package scanner;

import scanner.characterList.CharacterList;
import scanner.datatypes.TerminalType;
import scanner.datatypes.Terminal;
import scanner.errors.LexicalError;
import scanner.states.ITokenState;
import scanner.states.IdentTokenState;
import scanner.characterList.ICharacterList;
import scanner.tokenList.ITokenList;
import scanner.tokenList.TokenList;

import java.util.List;

/**
 * Created by tobi on 27/09/16.
 */
public class Scanner {
    private ITokenState tokenState;
    private ITokenList tokenList;

    public Scanner() {
    }

    public static void printResult(String imlCode, String result, ITokenList tokenList) {
        System.out.printf("%s\n", imlCode);
        if (result != null) {
            System.out.printf("%s\n", result);
        }
        System.out.printf("%s\n\n", tokenList.toString());
    }

    public ITokenList scan(String text) throws LexicalError {
        tokenState = new IdentTokenState(this);
        ICharacterList characterList = new CharacterList(text);
        tokenList = new TokenList();
        while((tokenState = tokenState.scan(characterList)) != null);
        return tokenList;
    }

    public void setState(ITokenState state) {
        this.tokenState = state;
    }

    public ITokenList getTokenList() {
        return this.tokenList;
    }
}