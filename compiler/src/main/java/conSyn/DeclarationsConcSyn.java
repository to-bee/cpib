package conSyn;

import absSyn.IAbsSyn;
import absSyn.ProgramParameterListAbsSyn;
import scanner.datatypes.Terminal;
import scanner.datatypes.TerminalType;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

import absSyn.DeclarationsAbsSyn;
import scanner.token.IToken;

import java.util.List;
/**
 * Created by tobi on 17.12.16.
 */
public class DeclarationsConcSyn extends AbstractConcSyn implements IConcSyn {
    public DeclarationsConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    private IToken token;
    @Override
    public DeclarationsAbsSyn toAbsSyn() throws ContextError {
        //Für jedes Nichtterminalsymbol (unten mit ParseNext deklariert) wird eine Liste mit den dazugehörigen Elementen dem Abstrakten Syntaxbaum übergeben.
        List<IAbsSyn> DeclarationConcSyn = super.getListByType(DeclarationConcSyn.class);
        List<IAbsSyn> RepeatingOptionalDeclarationsConcSyn = super.getListByType(RepeatingOptionalDeclarationsConcSyn.class);

        return new DeclarationsAbsSyn(token, DeclarationConcSyn, RepeatingOptionalDeclarationsConcSyn);
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT
                || getTokenList().getCurrent().getTerminal() == Terminal.FUN
                || getTokenList().getCurrent().getTerminal() == Terminal.PROC
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.CHANGEMODE) {
            this.parseNext(new DeclarationConcSyn(this.getTokenList(), getCounter()));
            this.parseNext(new RepeatingOptionalDeclarationsConcSyn(this.getTokenList(), getCounter()));
        } else {
            throwGrammarError();
        }
    }
}
