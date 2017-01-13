package conSyn;

import absSyn.ProgramAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.token.Ident;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class ProgramConcSyn extends AbstractConcSyn {
    private Ident ident;
    private ProgramParameterListConcSyn programParameterListConcSyn;
    private OptionalGlobalDeclarationsConcSyn optionalGlobalDeclarationsConcSyn;
    private BlockCmdConcSyn blockCmdConcSyn;
    private Terminal terminal;

    public ProgramConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public ProgramAbsSyn toAbsSyn() throws ContextError {
        return new ProgramAbsSyn(ident,
                programParameterListConcSyn.toAbsSyn(),
                optionalGlobalDeclarationsConcSyn.toAbsSyn(),
                blockCmdConcSyn.toAbsSyn(),
                terminal);
    }

    @Override
    public void parse() throws GrammarError {
        this.terminal = getTokenList().getCurrent().getTerminal();
        if (this.terminal == Terminal.PROGRAM) {
            consume();
            this.terminal = getTokenList().getCurrent().getTerminal();
            if (this.terminal == Terminal.IDENT) {
                ident = (Ident) this.getTokenList().getCurrent();
                consume();

                programParameterListConcSyn = new ProgramParameterListConcSyn(getTokenList(), getCounter());
                parseNext(programParameterListConcSyn);

                optionalGlobalDeclarationsConcSyn = new OptionalGlobalDeclarationsConcSyn(getTokenList(), getCounter());
                parseNext(optionalGlobalDeclarationsConcSyn);
                this.terminal = getTokenList().getCurrent().getTerminal();

                if (this.terminal == Terminal.DO) {
                    consume();
                    blockCmdConcSyn = new BlockCmdConcSyn(getTokenList(), getCounter());
                    parseNext(blockCmdConcSyn);
                    this.terminal = getTokenList().getCurrent().getTerminal();
                    if (this.terminal == Terminal.ENDPROGRAM) {
                        consume();
                    } else {
                        throwGrammarError();
                    }
                } else {
                    throwGrammarError();
                }
            } else {
                throwGrammarError();
            }
        } else {
            throwGrammarError();
        }
    }
}
