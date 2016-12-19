package conSyn;

import absSyn.IAbsSyn;
import scanner.datatypes.TerminalType;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

/**
 * Created by tobi on 17.12.16.
 */
public class Declaration extends AbstractConcSyn implements IConcSyn {
    public Declaration(ITokenList tokenList, int i) {
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
            parseNext(new StorageDeclaration(getTokenList(), getCounter()));
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.FUN) {
            parseNext(new FunctionDeclaration(getTokenList(), getCounter()));
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.PROC) {
            parseNext(new ProcedureDeclaration(getTokenList(), getCounter()));
        } else {
            throwGrammarError();
        }
    }
}
