package conSyn;

import absSyn.IAbsSyn;
import absSyn.ProgramParameterListAbsSyn;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.token.Ident;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

import absSyn.ProcedureDeclarationAbsSyn;
import scanner.token.IToken;

import java.util.List;
/**
 * Created by tobi on 17.12.16.
 */
public class ProcedureDeclarationConcSyn extends AbstractConcSyn implements IConcSyn {
    private BlockCmdConcSyn blockCmdConcSyn;
    private ParameterListConcSyn parameterListConcSyn;
    private OptionalGlobalImportsConcSyn optionalGlobalImportsConcSyn;
    private OptionalLocalStorageDeclarationsConcSyn optionalLocalStorageDeclarationsConcSyn;
    private Ident ident;
    private Terminal terminal;
    public ProcedureDeclarationConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public ProcedureDeclarationAbsSyn toAbsSyn() throws ContextError {
        return new ProcedureDeclarationAbsSyn(
                ident,
                parameterListConcSyn.toAbsSyn(),
                optionalGlobalImportsConcSyn.toAbsSyn(),
                optionalLocalStorageDeclarationsConcSyn.toAbsSyn(),
                blockCmdConcSyn.toAbsSyn(),
                terminal);
    }

    /**
     * TODO rewrite Ident
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        this.terminal = getTokenList().getCurrent().getTerminal();
        if (this.terminal  == Terminal.PROC) {
            consume();
            this.terminal = getTokenList().getCurrent().getTerminal();
            if (this.terminal == Terminal.IDENT) {
                ident = (Ident) this.getTokenList().getCurrent();
                consume();

                parameterListConcSyn = new ParameterListConcSyn(getTokenList(), getCounter());
                parseNext(parameterListConcSyn);

                optionalGlobalImportsConcSyn = new OptionalGlobalImportsConcSyn(getTokenList(), getCounter());
                parseNext(optionalGlobalImportsConcSyn);

                optionalLocalStorageDeclarationsConcSyn = new OptionalLocalStorageDeclarationsConcSyn(getTokenList(), getCounter());
                parseNext(optionalLocalStorageDeclarationsConcSyn);

                this.terminal = getTokenList().getCurrent().getTerminal();
                if (this.terminal == Terminal.DO) {
                    consume();

                    blockCmdConcSyn = new BlockCmdConcSyn(getTokenList(), getCounter());
                    parseNext(blockCmdConcSyn);
                    this.terminal = getTokenList().getCurrent().getTerminal();
                    if (this.terminal == Terminal.ENDPROC) {
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
        }
    }
}