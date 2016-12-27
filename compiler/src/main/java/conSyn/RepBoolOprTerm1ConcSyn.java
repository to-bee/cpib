package conSyn;

import absSyn.IAbsSyn;
import absSyn.ProgramParameterListAbsSyn;
import scanner.datatypes.TerminalType;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

import absSyn.RepBoolOprTerm1AbsSyn;
import scanner.token.IToken;

import java.util.List;
/**
 * Created by tobi on 17.12.16.
 */
public class RepBoolOprTerm1ConcSyn extends AbstractConcSyn implements IConcSyn {
    public RepBoolOprTerm1ConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public RepBoolOprTerm1AbsSyn toAbsSyn() throws ContextError {
        //Für jedes Nichtterminalsymbol (unten mit ParseNext deklariert) wird eine Liste mit den dazugehörigen Elementen dem Abstrakten Syntaxbaum übergeben.
        List<IAbsSyn> Term1ConcSyn = super.getListByType(Term1ConcSyn.class);
        List<IAbsSyn> RepBoolOprTerm1ConcSyn = super.getListByType(RepBoolOprTerm1ConcSyn.class);

        return new RepBoolOprTerm1AbsSyn(token, Term1ConcSyn, RepBoolOprTerm1ConcSyn);
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.RPAREN
                || getTokenList().getCurrent().getTerminal() == Terminal.COMMA
                || getTokenList().getCurrent().getTerminal() == Terminal.DO
                || getTokenList().getCurrent().getTerminal() == Terminal.THEN
                || getTokenList().getCurrent().getTerminal() == Terminal.ENDPROC
                || getTokenList().getCurrent().getTerminal() == Terminal.ENDFUN
                || getTokenList().getCurrent().getTerminal() == Terminal.ENDWHILE
                || getTokenList().getCurrent().getTerminal() == Terminal.ENDIF
                || getTokenList().getCurrent().getTerminal() == Terminal.ELSE
                || getTokenList().getCurrent().getTerminal() == Terminal.ENDPROGRAM
                || getTokenList().getCurrent().getTerminal() == Terminal.SEMICOLON
                || getTokenList().getCurrent().getTerminal() == Terminal.BECOMES) {

        } else if (getTokenList().getCurrent().getTerminal().getType() == TerminalType.BOOLOPR) {
            consume();
            parseNext(new Term1ConcSyn(getTokenList(), getCounter()));
            parseNext(new RepBoolOprTerm1ConcSyn(getTokenList(), getCounter()));
        } else {
            throwGrammarError();
        }
    }
}
