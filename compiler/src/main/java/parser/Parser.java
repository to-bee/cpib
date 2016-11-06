package parser;

import conSyn.ConcSyn;
import conSyn.IConcSyn;
import model.datatypes.Terminals;
import model.errors.GrammarError;
import model.errors.LexicalError;
import model.token.IToken;
import tokenList.ITokenList;
import tokenList.TokenList;

/**
 * Created by tobi on 27/09/16.
 */
public class Parser implements IParser {

    private final ITokenList tokenList;
    private final IConcSyn concSyn;

    public Parser(ITokenList tokenList) {
        this.tokenList = tokenList.clone();
        this.concSyn = new ConcSyn();
    }

    /**
     * /**
     * Zusammenfassen in Faktoren (F) (Klammern setzen)
     * Terme erstellen (T)
     * Expression erstellen (E)
     *
     * E := T + T + .. + T
     * T := F * F * .. * F
     * F := id | (E)
     * Recursive!
     *
     * Linksassoziativ:
     * id * id * id
     * (id * id) * id
     * _________   __
     *     E       T
     * Grammatik: E := T | E * T
     *
     * Rechtsassoziativ:
     * id * id * id
     * id * (id * id)
     * __ * _________
     *    T     E
     *
     * Grammatik:
     * E := T | T * E
     *
     *
     *
     * classes:
     * EParser : IParser
     * TParser : IParser
     * FParser : IParser
     *
     * Problem 1
     * Löst Rekursion aus
     * Linksassoziativität wird durch rechtsassoziativität ersetzt
     * Muss später im abstrakten Parsebaum zurückgeändert werden
     * E := T
     * E := E * T
     * E .= T E'
     * E' := * T E'
     * E' .= €
     *
     *
     * Problem 2
     * Löst Rekursion auf
     * Ist nachher immer noch rechtsassoziativ
     * T := F | F * T
     *
     * T := F T'
     * T' := * F T'
     * T' := €
     * T' = das, was vielleicht noch hinten dran kommt
     *
     *
     * http://www.u-helmich.de/inf/BlueJ/kurs131/Seite31/seite31-1.html
     *
     * Neue Gesamtgrammatik
     * E := TE'
     * E' := * TE'
     * E' := €
     * T := FT'
     * T' := * FT'
     * T' := €
     * F := id
     * F := (E)
     *
     * Aufgabe
     * id + id * id
     * id + (id * id)
     * ((id))
     * id + id + id * id
     *
     *
     *
     *
     * while
     *  Find smallest precedence
     *  If you find two with the same priority, take the left one
     *  Take element left and element right of the operator -> Create leaf
     *  Remove Elements from tokenList
     *
     *
     *      Go on recursive for the left elements and right elements:
     *
     *      Find smallest precedence on the left side
     *      Insert leaf left
     *
     *      Find smallest precedence on the right side
     *      Insert leaft right right
     *
     *
     *
     *      Basis parser.Parser methode (consume)
     *      Linksassoziativ durchgehen
     *      Wenn etwas nicht erwartet wird: Syntaxfehler
     *
     *      Program parst zuerst das ganze Programm
     *      Gleich viele ( klammern wie ) klammern
     *      Danach consume: wenn ( -> subparser öffnen
     *
     *
     * //        fun(while) -> expr -> DO -> cpsCMD (return)
     *
     * @return
     */
    @Override
    public IConcSyn parse() throws GrammarError {
        IToken next = tokenList.nextToken();
        consumeProgram(next);
        return concSyn;
    }

    /**
     * Replaces NonTerminals with Terminals
     * Creates a nested Tree
     * Input from Scanner: [(IDENT, "a"), (ASSIGNOPR, BECOMES), (LITERAL, 67.0), PLUS, (LITERAL, 31.0), MINUS, (LITERAL, 2.0), SENTINEL]
     */
    public void consumeProgram(IToken token) throws GrammarError {
        this.concSyn.add(token);
        IToken next = tokenList.nextToken();
        if (next.getTerminal() == Terminals.WHILE) {
            consumeWhile(next);
        }
    }

    public void consumeWhile(IToken token) throws GrammarError {
        this.concSyn.add(token);
        IToken next = tokenList.nextToken();
        if (next.getTerminal() == Terminals.IDENT) {
            consumeIdent(next);
        }
    }

    private void consumeIdent(IToken token) throws GrammarError {
        this.concSyn.add(token);
        IToken next = tokenList.nextToken();
        if (next.getTerminal() == Terminals.RELOPR) {
            consumeRelopr(next);
        }
    }

    private void consumeRelopr(IToken token) throws GrammarError {
        this.concSyn.add(token);
        IToken next = tokenList.nextToken();
        if (next.getTerminal() == Terminals.SENTINEL) {
            consumeSentinel(next);
        }
    }

    /**
     * Parser terminates
     * @param last
     * @throws GrammarError
     */
    private void consumeSentinel(IToken last) throws GrammarError {
        this.concSyn.add(last);
        if(tokenList.size() > 0) {
            throw new GrammarError(String.format("%s must be the last token", Terminals.SENTINEL));
        }
    }


//        if(terminal == Terminals.PROGRAM) {
//            consume
//        }
//        if(terminal == Terminals.SENTINEL)
//            return;
//
//        if(token != terminal) {
//            throw parseerror();
//        }
//
//        if(token == IF) {
//            expr();
//        }



    private ITokenList getPossibleFollowingToken(Terminals terminal, Terminals followingTerminal) throws GrammarError {
        throw new GrammarError(String.format("%s cannot follow to %s", terminal, followingTerminal));
    }

//    public void expr() {
//        consume(THEN);
//        cpsCmd();
//        consume(ELSE)
//        cpsCmd();
//        consume(ENDIF)
//    }

    /**
     * TODO: Iml_v2 page 17
     */
//    public void cpsCmd() {
//       consume(cmd);
//        while(SEMICOLON) {
//            consume(cmd);
//        }
//    }


}