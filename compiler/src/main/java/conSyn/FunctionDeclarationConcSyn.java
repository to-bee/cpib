package conSyn;

import absSyn.FunctionDeclarationAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.token.Ident;
import scanner.tokenList.ITokenList;
/**
 * Created by tobi on 17.12.16.
 */
public class FunctionDeclarationConcSyn extends AbstractConcSyn implements IConcSyn {
    private Ident ident;
    private ParameterListConcSyn parameterListConcSyn;
    private BlockCmdConcSyn blockCmdConcSyn;
    private StorageDeclarationConcSyn storageDeclarationConcSyn;
    private OptionalGlobalImportsConcSyn optionalGlobalImportsConcSyn;
    private OptionalLocalStorageDeclarationsConcSyn optionalLocalStorageDeclarationsConcSyn;

    public FunctionDeclarationConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public FunctionDeclarationAbsSyn toAbsSyn() throws ContextError {
        return new FunctionDeclarationAbsSyn(
                ident,
                parameterListConcSyn.toAbsSyn(),
                storageDeclarationConcSyn.toAbsSyn(),
                optionalGlobalImportsConcSyn.toAbsSyn(),
                optionalLocalStorageDeclarationsConcSyn.toAbsSyn(),
                blockCmdConcSyn.toAbsSyn());
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.FUN) {
            consume();
            if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT) {
                ident = (Ident) this.getTokenList().getCurrent();
                consume();
                parameterListConcSyn = new ParameterListConcSyn(getTokenList(), getCounter());
                parseNext(parameterListConcSyn);
                if (getTokenList().getCurrent().getTerminal() == Terminal.RETURNS) {
                    consume();

                    storageDeclarationConcSyn = new StorageDeclarationConcSyn(getTokenList(), getCounter());
                    parseNext(storageDeclarationConcSyn);

                    optionalGlobalImportsConcSyn = new OptionalGlobalImportsConcSyn(getTokenList(), getCounter());
                    parseNext(optionalGlobalImportsConcSyn);

                    optionalLocalStorageDeclarationsConcSyn = new OptionalLocalStorageDeclarationsConcSyn(getTokenList(), getCounter());
                    parseNext(optionalLocalStorageDeclarationsConcSyn);

                    if (getTokenList().getCurrent().getTerminal() == Terminal.DO) {
                        consume();

                        blockCmdConcSyn = new BlockCmdConcSyn(getTokenList(), getCounter());
                        parseNext(blockCmdConcSyn);

                        if (getTokenList().getCurrent().getTerminal() == Terminal.ENDFUN) {
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
        } else {
            throwGrammarError();
        }
    }
}
