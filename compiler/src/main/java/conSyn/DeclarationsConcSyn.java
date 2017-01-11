package conSyn;

import absSyn.DeclarationsAbsSyn;
import scanner.datatypes.Terminal;
import scanner.datatypes.TerminalType;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
/**
 * Created by tobi on 17.12.16.
 */
public class DeclarationsConcSyn extends AbstractConcSyn implements IConcSyn {
    private DeclarationConcSyn declarationConcSyn;
    private RepeatingOptionalDeclarationsConcSyn repeatingOptionalDeclarationsConcSyn;
    private Terminal terminal;

    public DeclarationsConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public DeclarationsAbsSyn toAbsSyn() throws ContextError {
        return new DeclarationsAbsSyn(declarationConcSyn.toAbsSyn(),repeatingOptionalDeclarationsConcSyn.toAbsSyn());
    }


    @Override
    public void parse() throws GrammarError {
        this.terminal = getTokenList().getCurrent().getTerminal();
        if (this.terminal == Terminal.IDENT
                || this.terminal == Terminal.FUN
                || this.terminal == Terminal.PROC
                || this.terminal.getType() == TerminalType.CHANGEMODE) {
            declarationConcSyn = new DeclarationConcSyn(this.getTokenList(), getCounter());
            this.parseNext(declarationConcSyn);
            repeatingOptionalDeclarationsConcSyn = new RepeatingOptionalDeclarationsConcSyn(this.getTokenList(), getCounter());
            this.parseNext(repeatingOptionalDeclarationsConcSyn);
        } else {
            throwGrammarError();
        }
    }
}
