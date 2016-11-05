package parser;

import conSyn.ConcSyn;
import conSyn.IConcSyn;
import model.datatypes.Terminals;
import model.errors.GrammarError;
import model.token.IToken;
import tokenList.ITokenList;

/**
 * Created by tobi on 27/09/16.
 */
public class Parser implements IParser {

    private final ITokenList tokenList;
    private final IConcSyn concSyn;
    private IToken next;

    public Parser(ITokenList tokenList) {
        this.tokenList = tokenList.clone();
        this.concSyn = new ConcSyn();
    }

    /**
     * /**
     * Zusammenfassen in Faktoren (F) (Klammern setzen)
     * Terme erstellen (T)
     * Expression erstellen (E)
     * <p>
     * E := T + T + .. + T
     * T := F * F * .. * F
     * F := id | (E)
     * Recursive!
     * <p>
     * Linksassoziativ:
     * id * id * id
     * (id * id) * id
     * _________   __
     * E       T
     * Grammatik: E := T | E * T
     * <p>
     * Rechtsassoziativ:
     * id * id * id
     * id * (id * id)
     * __ * _________
     * T     E
     * <p>
     * Grammatik:
     * E := T | T * E
     * <p>
     * <p>
     * <p>
     * classes:
     * EParser : IParser
     * TParser : IParser
     * FParser : IParser
     * <p>
     * Problem 1
     * Löst Rekursion aus
     * Linksassoziativität wird durch rechtsassoziativität ersetzt
     * Muss später im abstrakten Parsebaum zurückgeändert werden
     * E := T
     * E := E * T
     * E .= T E'
     * E' := * T E'
     * E' .= €
     * <p>
     * <p>
     * Problem 2
     * Löst Rekursion auf
     * Ist nachher immer noch rechtsassoziativ
     * T := F | F * T
     * <p>
     * T := F T'
     * T' := * F T'
     * T' := €
     * T' = das, was vielleicht noch hinten dran kommt
     * <p>
     * <p>
     * http://www.u-helmich.de/inf/BlueJ/kurs131/Seite31/seite31-1.html
     * <p>
     * Neue Gesamtgrammatik
     * E := TE'
     * E' := * TE'
     * E' := €
     * T := FT'
     * T' := * FT'
     * T' := €
     * F := id
     * F := (E)
     * <p>
     * Aufgabe
     * id + id * id
     * id + (id * id)
     * ((id))
     * id + id + id * id
     * <p>
     * <p>
     * <p>
     * <p>
     * while
     * Find smallest precedence
     * If you find two with the same priority, take the left one
     * Take element left and element right of the operator -> Create leaf
     * Remove Elements from tokenList
     * <p>
     * <p>
     * Go on recursive for the left elements and right elements:
     * <p>
     * Find smallest precedence on the left side
     * Insert leaf left
     * <p>
     * Find smallest precedence on the right side
     * Insert leaft right right
     * <p>
     * <p>
     * <p>
     * Basis parser.Parser methode (consume)
     * Linksassoziativ durchgehen
     * Wenn etwas nicht erwartet wird: Syntaxfehler
     * <p>
     * Program parst zuerst das ganze Programm
     * Gleich viele ( klammern wie ) klammern
     * Danach consume: wenn ( -> subparser öffnen
     * <p>
     * <p>
     * //        fun(while) -> expr -> DO -> cpsCMD (return)
     *
     * @return
     */
    @Override
    public IConcSyn parseProgram() throws GrammarError {
        // loads first token and checks if it starts with program
        next = tokenList.nextToken();
        if (next.getTerminal() == Terminals.PROGRAM) {
            // consume program
            consume();
            // check if netxt token is identifier
            if (next.getTerminal() == Terminals.IDENT) {
                consume();
                programParameterList();
            } else {
                throwGrammarError();
            }
        } else {
            throwGrammarError();
        }

        return concSyn;
    }

    /**
     * TODO: output position from Scanner
     * @throws GrammarError
     */
    private void throwGrammarError() throws GrammarError {
        throw new GrammarError(String.format("Not a correct Grammar: %s", this.next.toString()));
    }

    /**
     * line 173
     * @throws GrammarError
     */
    private void programParameterList() throws GrammarError {
        if (next.getTerminal() == Terminals.LPAREN) {
            consume();
            optionalProgramParam();
        } else {
            throwGrammarError();
        }
    }

    private void optionalProgramParam() throws GrammarError {
        if (next.getTerminal() == Terminals.RPAREN) {
            consume();
        }
        else if (next.getTerminal() == Terminals.IDENT) {
            optionalFlowMode();
            optionalChangeMode();
            typeIdent();
            repeatingOptionalProgramParameters();
        }
        else if (next.getTerminal() == Terminals.CHANGEMODE) {
            optionalFlowMode();
            optionalChangeMode();
            typeIdent();
            repeatingOptionalProgramParameters();
        }
        else if (next.getTerminal() == Terminals.FLOWMODE) {
            optionalFlowMode();
            optionalChangeMode();
            typeIdent();
            repeatingOptionalProgramParameters();
        } else {
            throwGrammarError();
        }
    }

    private void repeatingOptionalProgramParameters() {

    }

    private void typeIdent() throws GrammarError {
        if (next.getTerminal() == Terminals.IDENT) {
            consume();
            if (next.getTerminal() == Terminals.IDENT) {
                consume();
                if (next.getTerminal() == Terminals.COLON) {
                    consume();
                    typeDeclaration();
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

    private void typeDeclaration() {
        // TODO: implement this
    }

    private void optionalChangeMode() throws GrammarError {
        if (next.getTerminal() == Terminals.IDENT) {
            consume();
        }
        else if (next.getTerminal() == Terminals.CHANGEMODE) {
            consume();
            if (next.getTerminal() == Terminals.CHANGEMODE) {
                consume();
            } else {
                throwGrammarError();
            }
        } else {
            throwGrammarError();
        }
    }

    private void optionalFlowMode() throws GrammarError {
        if (next.getTerminal() == Terminals.MECHMODE) {
            consume();
        }
        else if (next.getTerminal() == Terminals.IDENT) {
            consume();
        }
        else if (next.getTerminal() == Terminals.CHANGEMODE) {
            consume();
        }
        else if (next.getTerminal() == Terminals.FLOWMODE) {
            consume();
            if(next.getTerminal() == Terminals.FLOWMODE) {
                consume();
            } else {
                throwGrammarError();
            }
        } else {
            throwGrammarError();
        }
    }

    /**
     * Loads the next token
     * @throws GrammarError
     */
    public void consume() throws GrammarError {
        next = tokenList.nextToken();
    }

    public void consumeWhile(IToken token) throws GrammarError {
        this.concSyn.add(token);
        IToken next = tokenList.nextToken();
        if (next.getTerminal() == Terminals.IDENT) {
            consumeIdent(next);
        } else {
            throw new GrammarError("Not a correct Grammar: line xy, token: xy");
        }
    }

    private void consumeIdent(IToken token) throws GrammarError {
        this.concSyn.add(token);
        IToken next = tokenList.nextToken();
        if (next.getTerminal() == Terminals.RELOPR) {
            consumeRelopr(next);
        } else {
            throw new GrammarError("Not a correct Grammar: line xy, token: xy");
        }
    }

    private void consumeRelopr(IToken token) throws GrammarError {
        this.concSyn.add(token);
        IToken next = tokenList.nextToken();
        if (next.getTerminal() == Terminals.SENTINEL) {
            consumeSentinel(next);
        } else {
            throw new GrammarError("Not a correct Grammar: line xy, token: xy");
        }
    }

    /**
     * Parser terminates
     *
     * @param last
     * @throws GrammarError
     */
    private void consumeSentinel(IToken last) throws GrammarError {
        this.concSyn.add(last);
        if (tokenList.size() > 0) {
            throw new GrammarError(String.format("%s must be the last token", Terminals.SENTINEL));
        } else {
            throw new GrammarError("Not a correct Grammar: line xy, token: xy");
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