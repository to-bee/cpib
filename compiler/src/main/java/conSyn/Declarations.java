package conSyn;

import scanner.datatypes.Terminal;
import scanner.datatypes.TerminalType;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class Declarations extends AbstractConcSyn implements IConcSyn {
    public Declarations(ITokenList tokenList) {
        super(tokenList);
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT
                || getTokenList().getCurrent().getTerminal() == Terminal.FUN
                || getTokenList().getCurrent().getTerminal() == Terminal.PROC
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.CHANGEMODE) {
            this.parseNext(new Declaration(this.getTokenList()));
            this.parseNext(new RepeatingOptionalDeclarations(this.getTokenList()));
        } else {
            throwGrammarError();
        }
    }
}
