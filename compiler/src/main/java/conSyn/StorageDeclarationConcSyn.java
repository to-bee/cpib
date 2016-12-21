package conSyn;

import absSyn.IAbsSyn;
import scanner.datatypes.TerminalType;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

/**
 * Created by tobi on 17.12.16.
 */
public class StorageDeclarationConcSyn extends AbstractConcSyn implements IConcSyn {
    public StorageDeclarationConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public IAbsSyn toAbsSyn() {
        //TODO: implement
        return null;
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.CHANGEMODE) {
            parseNext(new OptionalChangeModeConcSyn(getTokenList(), getCounter()));
            parseNext(new TypedIdentConcSyn(getTokenList(), getCounter()));
        } else {
            throwGrammarError();
        }
    }
}
