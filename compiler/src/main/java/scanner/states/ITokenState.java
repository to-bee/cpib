package scanner.states;

import scanner.characterList.ICharacterList;

/**
 * Created by tobi on 12.12.16.
 */
public interface ITokenState {
    ITokenState scan(ICharacterList character);
}
