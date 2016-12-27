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
    public FunctionDeclarationConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    private IToken token;
    @Override
    public IAbsSyn toAbsSyn() throws ContextError {
        //Für jedes Nichtterminalsymbol (unten mit ParseNext deklariert) wird eine Liste mit den dazugehörigen Elementen dem Abstrakten Syntaxbaum übergeben.
        List<IAbsSyn> ParameterListConcSyn = super.getListByType(ParameterListConcSyn.class);
        List<IAbsSyn> StorageDeclarationConcSyn = super.getListByType(StorageDeclarationConcSyn.class);
        List<IAbsSyn> OptionalGlobalImportsConcSyn = super.getListByType(OptionalGlobalImportsConcSyn.class);
        List<IAbsSyn> OptionalLocalStorageDeclarationsConcSyn = super.getListByType(OptionalLocalStorageDeclarationsConcSyn.class);
        List<IAbsSyn> BlockCmdConcSyn = super.getListByType(BlockCmdConcSyn.class);

        return new FunctionDeclarationAbsSyn(token, ParameterListConcSyn, StorageDeclarationConcSyn, OptionalGlobalImportsConcSyn, OptionalLocalStorageDeclarationsConcSyn, BlockCmdConcSyn);
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
                    parseNext(new StorageDeclarationConcSyn(getTokenList(), getCounter()));
                    parseNext(new OptionalGlobalImportsConcSyn(getTokenList(), getCounter()));
                    parseNext(new OptionalLocalStorageDeclarationsConcSyn(getTokenList(), getCounter()));
                    if (getTokenList().getCurrent().getTerminal() == Terminal.DO) {
                        consume();
                        parseNext(new BlockCmdConcSyn(getTokenList(), getCounter()));
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