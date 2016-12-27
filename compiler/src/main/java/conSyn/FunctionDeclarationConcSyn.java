package conSyn;

import absSyn.IAbsSyn;
import absSyn.ProgramParameterListAbsSyn;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

import absSyn.FunctionDeclarationAbsSyn;
import scanner.token.IToken;

import java.util.List;
/**
 * Created by tobi on 17.12.16.
 */
public class FunctionDeclarationConcSyn extends AbstractConcSyn implements IConcSyn {
    private BlockCmdConcSyn blockCmdConcSyn;
    private StorageDeclarationConcSyn storageDeclarationConcSyn;
    private OptionalGlobalImportsConcSyn optionalGlobalImportsConcSyn;
    private OptionalLocalStorageDeclarationsConcSyn optionalLocalStorageDeclarationsConcSyn;

    public FunctionDeclarationConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    private IToken token;
    @Override
    public FunctionDeclarationAbsSyn toAbsSyn() throws ContextError {
        return new FunctionDeclarationAbsSyn(
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
                consume();
                parseNext(new ParameterListConcSyn(getTokenList(), getCounter()));
                if (getTokenList().getCurrent().getTerminal() == Terminal.RETURNS) {
                    consume();

                    storageDeclarationConcSyn = new StorageDeclarationConcSyn(getTokenList(), getCounter());
                    parseNext(storageDeclarationConcSyn);

                    optionalGlobalImportsConcSyn = new OptionalGlobalImportsConcSyn(getTokenList(), getCounter());
                    parseNext(optionalGlobalImportsConcSyn);

                    optionalLocalStorageDeclarationsConcSyn = new OptionalLocalStorageDeclarationsConcSyn(getTokenList(), getCounter())
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
