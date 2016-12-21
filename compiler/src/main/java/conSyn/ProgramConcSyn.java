package conSyn;

import absSyn.IAbsSyn;
import absSyn.ProgramAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.token.IToken;
import scanner.tokenList.ITokenList;

import java.util.List;

/**
 * Created by tobi on 17.12.16.
 */
public class ProgramConcSyn extends AbstractConcSyn {
    private IToken token;

    public ProgramConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public IAbsSyn toAbsSyn() throws ContextError {
        //Für jedes Nichtterminalsymbol (unten mit ParseNext deklariert) wird eine Liste mit den dazugehörigen Elementen dem Abstrakten Syntaxbaum übergeben.
        List<IAbsSyn> OptionalGlobalDeclarations = super.getListByType(OptionalGlobalDeclarationsConcSyn.class);
        List<IAbsSyn> ProgramParameterList = super.getListByType(ProgramParameterListConcSyn.class);
        IAbsSyn BlockCmd = super.getOneByType(BlockCmdConcSyn.class);

        return new ProgramAbsSyn(token, ProgramParameterList, OptionalGlobalDeclarations,BlockCmd);
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.PROGRAM) {
            this.token = getTokenList().getCurrent();
            consume();
            if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT) {
                consume();

                parseNext(new ProgramParameterListConcSyn(getTokenList(), getCounter()));
                parseNext(new OptionalGlobalDeclarationsConcSyn(getTokenList(), getCounter()));

                if (getTokenList().getCurrent().getTerminal() == Terminal.DO) {
                    consume();
                    parseNext(new BlockCmdConcSyn(getTokenList(), getCounter()));
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
