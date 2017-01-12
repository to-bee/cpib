package conSyn;

import absSyn.DeclarationAbsSyn;
import absSyn.IAbsSyn;
import scanner.datatypes.Terminal;
import scanner.datatypes.TerminalType;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class DeclarationConcSyn extends AbstractConcSyn implements IConcSyn {
    private IConcSyn subtype;
    private Terminal terminal;

    public DeclarationConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public DeclarationAbsSyn toAbsSyn() throws ContextError {
        return new DeclarationAbsSyn(subtype.toAbsSyn());
    }

    /**
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        this.terminal = getTokenList().getCurrent().getTerminal();
        if (this.terminal == Terminal.IDENT
                || this.terminal.getType() == TerminalType.CHANGEMODE) {
            subtype = new StorageDeclarationConcSyn(getTokenList(), getCounter());
        } else if (this.terminal == Terminal.FUN) {
            subtype = new FunctionDeclarationConcSyn(getTokenList(), getCounter());
        } else if (this.terminal == Terminal.PROC) {
            subtype = new ProcedureDeclarationConcSyn(getTokenList(), getCounter());
        } else {
            throwGrammarError();
        }
        parseNext(subtype);
    }
}
