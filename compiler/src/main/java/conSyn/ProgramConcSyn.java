package conSyn;

import absSyn.IAbsSyn;
import absSyn.ProgramAbsSyn;
import absSyn.ProgramParameterListAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.token.Ident;
import scanner.tokenList.ITokenList;
import java.util.List;

/**
 * Created by tobi on 17.12.16.
 */
public class ProgramConcSyn extends AbstractConcSyn {
    private Ident ident;
    private ProgramParameterListConcSyn programParameterListConcSyn;
    private OptionalGlobalDeclarationsConcSyn optionalGlobalDeclarationsConcSyn;
    private BlockCmdConcSyn blockCmdConcSyn;

    public ProgramConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public ProgramAbsSyn toAbsSyn() throws ContextError {
        return new ProgramAbsSyn(ident, programParameterListConcSyn.toAbsSyn(), optionalGlobalDeclarationsConcSyn.toAbsSyn(), blockCmdConcSyn.toAbsSyn());
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.PROGRAM) {
            consume();
            if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT) {
                ident = (Ident) this.getTokenList().getCurrent();
                consume();

                programParameterListConcSyn = new ProgramParameterListConcSyn(getTokenList(), getCounter());
                parseNext(programParameterListConcSyn);

                optionalGlobalDeclarationsConcSyn = new OptionalGlobalDeclarationsConcSyn(getTokenList(), getCounter());
                parseNext(optionalGlobalDeclarationsConcSyn);

                if (getTokenList().getCurrent().getTerminal() == Terminal.DO) {
                    consume();
                    blockCmdConcSyn = new BlockCmdConcSyn(getTokenList(), getCounter());
                    parseNext(blockCmdConcSyn);
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
