package conSyn;

import absSyn.IAbsSyn;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

/**
 * Created by tobi on 17.12.16.
 */
public class FunctionDeclaration extends AbstractConcSyn implements IConcSyn {
    public FunctionDeclaration(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public IAbsSyn toAbsSyn() {
        //TODO
        return null;
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.FUN) {
            consume();
            if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT) {
                consume();
                parseNext(new ParameterList(getTokenList(), getCounter()));
                if (getTokenList().getCurrent().getTerminal() == Terminal.RETURNS) {
                    consume();
                    parseNext(new StorageDeclaration(getTokenList(), getCounter()));
                    parseNext(new OptionalGlobalImports(getTokenList(), getCounter()));
                    parseNext(new OptionalLocalStorageDeclarations(getTokenList(), getCounter()));
                    if (getTokenList().getCurrent().getTerminal() == Terminal.DO) {
                        consume();
                        parseNext(new BlockCmd(getTokenList(), getCounter()));
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
