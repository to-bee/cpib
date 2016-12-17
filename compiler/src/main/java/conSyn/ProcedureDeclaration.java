package conSyn;

import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

/**
 * Created by tobi on 17.12.16.
 */
public class ProcedureDeclaration extends AbstractConcSyn implements IConcSyn {
    public ProcedureDeclaration(ITokenList tokenList) {
        super(tokenList);
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.PROC) {
            consume();
            if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT) {
                consume();
                parseNext(new ParameterList(getTokenList()));
                parseNext(new OptionalGlobalImports(getTokenList()));
                parseNext(new OptionalLocalStorageDeclarations(getTokenList()));
                if (getTokenList().getCurrent().getTerminal() == Terminal.DO) {
                    consume();
                    parseNext(new BlockCmd(getTokenList()));
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