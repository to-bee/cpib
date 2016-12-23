package conSyn;

import absSyn.IAbsSyn;
import scanner.datatypes.Terminal;
import scanner.datatypes.TerminalType;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class DeclarationsConcSyn extends AbstractConcSyn implements IConcSyn {
    public DeclarationsConcSyn(ITokenList tokenList, int i) {
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
                || getTokenList().getCurrent().getTerminal() == Terminal.FUN
                || getTokenList().getCurrent().getTerminal() == Terminal.PROC
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.CHANGEMODE) {
            this.parseNext(new DeclarationConcSyn(this.getTokenList(), getCounter()));
            this.parseNext(new RepeatingOptionalDeclarationsConcSyn(this.getTokenList(), getCounter()));
        } else {
            throwGrammarError();
        }
    }
}
