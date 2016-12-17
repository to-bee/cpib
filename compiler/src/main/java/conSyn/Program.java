package conSyn;

import scanner.datatypes.Terminal;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class Program extends AbstractConcSyn {
    public Program(ITokenList tokenList) {

        super(tokenList);
    }
    @Override
    public void parse() throws GrammarError {
        // loads first token and checks if it starts with program
        consume();

        if (getTokenList().getCurrent().getTerminal() == Terminal.PROGRAM) {
            consume();
            if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT) {
                consume();

                parseNext(new ProgramParameterList(getTokenList()));
                parseNext(new OptionalGlobalImports(getTokenList()));

                if (getTokenList().getCurrent().getTerminal() == Terminal.DO) {
                    consume();
                    parseNext(new BlockCmd(getTokenList()));
                    if (getTokenList().getCurrent().getTerminal() == Terminal.ENDPROGRAM) {
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
