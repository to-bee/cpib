package conSyn;

import absSyn.ProcedureDeclarationAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.token.Ident;
import scanner.tokenList.ITokenList;
/**
 * Created by tobi on 17.12.16.
 */
public class ProcedureDeclarationConcSyn extends AbstractConcSyn implements IConcSyn {
    private BlockCmdConcSyn blockCmdConcSyn;
    private ParameterListConcSyn parameterListConcSyn;
    private OptionalGlobalImportsConcSyn optionalGlobalImportsConcSyn;
    private OptionalLocalStorageDeclarationsConcSyn optionalLocalStorageDeclarationsConcSyn;
    private Ident ident;

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
                blockCmdConcSyn.toAbsSyn());
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.PROC) {
            consume();
            if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT) {
                ident = (Ident) this.getTokenList().getCurrent();
                consume();

                parameterListConcSyn = new ParameterListConcSyn(getTokenList(), getCounter());
                parseNext(parameterListConcSyn);

                optionalGlobalImportsConcSyn = new OptionalGlobalImportsConcSyn(getTokenList(), getCounter());
                parseNext(optionalGlobalImportsConcSyn);

                optionalLocalStorageDeclarationsConcSyn = new OptionalLocalStorageDeclarationsConcSyn(getTokenList(), getCounter());
                parseNext(optionalLocalStorageDeclarationsConcSyn);

                if (getTokenList().getCurrent().getTerminal() == Terminal.DO) {
                    consume();

                    blockCmdConcSyn = new BlockCmdConcSyn(getTokenList(), getCounter());
                    parseNext(blockCmdConcSyn);
                    if (getTokenList().getCurrent().getTerminal() == Terminal.ENDPROC) {
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