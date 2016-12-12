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
                optionalGlobalDeclarations();
                if (next.getTerminal() == Terminals.DO) {
                    consume();
                    blockCmd();
                    if (next.getTerminal() == Terminals.ENDPROGRAM) {
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

        return concSyn;
    }

    private void optionalGlobalDeclarations() throws GrammarError {
        if (next.getTerminal() == Terminals.DO) {
            consume();
        } else if (next.getTerminal() == Terminals.GLOBAL) {
            consume();
            declarations();
        } else {
            throwGrammarError();
        }
    }

    private void declarations() throws GrammarError {
        if (next.getTerminal() == Terminals.PROC) {
            consume();
            declaration();
            repeatingOptionalDeclarations();
        } else if (next.getTerminal() == Terminals.FUN) {
            consume();
            declaration();
            repeatingOptionalDeclarations();
        } else if (next.getTerminal() == Terminals.IDENT) {
            consume();
            declaration();
            repeatingOptionalDeclarations();
        } else if (next.getTerminal() == Terminals.CHANGEMODE) {
            consume();
            declaration();
            repeatingOptionalProgramParameters();
        } else {
            throwGrammarError();
        }
    }

    private void repeatingOptionalDeclarations() throws GrammarError {
        if (next.getTerminal() == Terminals.DO) {
            consume();
        } else if (next.getTerminal() == Terminals.SEMICOLON) {
            consume();
            declaration();
            repeatingOptionalDeclarations();
        } else {
            throwGrammarError();
        }
    }

    private void declaration() throws GrammarError {
        if (next.getTerminal() == Terminals.IDENT) {
            consume();
            storageDeclaration();
        } else if (next.getTerminal() == Terminals.CHANGEMODE) {
            consume();
            storageDeclaration();
        } else if (next.getTerminal() == Terminals.FUN) {
            consume();
            functionDeclaration();
        } else if (next.getTerminal() == Terminals.PROC) {
            consume();
            procedureDeclaration();
        } else {
            throwGrammarError();
        }
    }

    private void storageDeclaration() throws GrammarError {
        if (next.getTerminal() == Terminals.IDENT) {
            consume();
            optionalChangeMode();
            typedIdent();
        } else if (next.getTerminal() == Terminals.CHANGEMODE) {
            consume();
            optionalChangeMode();
            typedIdent();
        } else {
            throwGrammarError();
        }
    }

    private void functionDeclaration() throws GrammarError {
        if (next.getTerminal() == Terminals.FUN) {
            consume();
            if (next.getTerminal() == Terminals.IDENT) {
                consume();
                parameterList();
                if (next.getTerminal() == Terminals.RETURNS) {
                    consume();
                    storageDeclaration();
                    optionalGlobalImports();
                    optionalLocalStorageDeclarations();
                    if (next.getTerminal() == Terminals.DO) {
                        consume();
                        blockCmd();
                        if (next.getTerminal() == Terminals.ENDFUN) {
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

    private void blockCmd() throws GrammarError {
        if (next.getTerminal() == Terminals.DEBUGOUT
                || next.getTerminal() == Terminals.DEBUGIN
                || next.getTerminal() == Terminals.CALL
                || next.getTerminal() == Terminals.WHILE
                || next.getTerminal() == Terminals.IF
                || next.getTerminal() == Terminals.REAL
                || next.getTerminal() == Terminals.IMAG
                || next.getTerminal() == Terminals.LPAREN
                || next.getTerminal() == Terminals.ADDOPR
                || next.getTerminal() == Terminals.NOT
                || next.getTerminal() == Terminals.IDENT
                || next.getTerminal() == Terminals.LITERAL
                || next.getTerminal() == Terminals.SKIP) {
            consume();
            cmd();
            repeatingOptionalCmds();
        } else {
            throwGrammarError();
        }
    }

    private void repeatingOptionalCmds() throws GrammarError {
        if (next.getTerminal() == Terminals.ENDPROC
                || next.getTerminal() == Terminals.ENDFUN
                || next.getTerminal() == Terminals.ENDWHILE
                || next.getTerminal() == Terminals.ENDIF
                || next.getTerminal() == Terminals.ELSE
                || next.getTerminal() == Terminals.ENDPROGRAM) {
            consume();
        }
        else if (next.getTerminal() == Terminals.SEMICOLON) {
            consume();
            cmd();
            repeatingOptionalCmds();
        } else {
            throwGrammarError();
        }
    }

    private void cmd() throws GrammarError {
        if (next.getTerminal() == Terminals.SKIP) {
            consume();
        } else if (next.getTerminal() == Terminals.REAL
                || next.getTerminal() == Terminals.IMAG
                || next.getTerminal() == Terminals.LPAREN
                || next.getTerminal() == Terminals.ADDOPR
                || next.getTerminal() == Terminals.NOT
                || next.getTerminal() == Terminals.IDENT
                || next.getTerminal() == Terminals.LITERAL) {
            consume();
            expression();
            if (next.getTerminal() == Terminals.BECOMES) {
                consume();
                expression();
            } else {
                throwGrammarError();
            }
        } else if (next.getTerminal() == Terminals.IF) {
            consume();
            expression();
            if (next.getTerminal() == Terminals.THEN) {
                consume();
                blockCmd();
                if (next.getTerminal() == Terminals.ELSE) {
                    consume();
                    blockCmd();
                    if (next.getTerminal() == Terminals.ENDIF) {
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
        } else if (next.getTerminal() == Terminals.WHILE) {
            consume();
            expression();
            if (next.getTerminal() == Terminals.DO) {
                consume();
                blockCmd();
                if (next.getTerminal() == Terminals.ENDWHILE) {
                    consume();
                } else {
                    throwGrammarError();
                }
            } else {
                throwGrammarError();
            }
        } else if (next.getTerminal() == Terminals.CALL) {
            consume();
            if (next.getTerminal() == Terminals.IDENT) {
                consume();
                expressionList();
                optionalGlobalInits();
            } else {
                throwGrammarError();
            }
        } else if (next.getTerminal() == Terminals.DEBUGIN
                || next.getTerminal() == Terminals.DEBUGOUT) {
            consume();
            expression();
        } else {
            throwGrammarError();
        }
    }

    private void optionalGlobalInits() throws GrammarError {
        if (next.getTerminal() == Terminals.ENDPROC
                || next.getTerminal() == Terminals.ENDFUN
                || next.getTerminal() == Terminals.ENDWHILE
                || next.getTerminal() == Terminals.ENDIF
                || next.getTerminal() == Terminals.ELSE
                || next.getTerminal() == Terminals.ENDPROGRAM
                || next.getTerminal() == Terminals.SEMICOLON) {
            consume();
        } else if (next.getTerminal() == Terminals.INIT) {
            consume();
            idents();
        } else {
            throwGrammarError();
        }
    }

    private void idents() throws GrammarError {
        if (next.getTerminal() == Terminals.SKIP) {
            consume();
            repeatingOptionalIdents();
        }else {
            throwGrammarError();
        }
    }

    private void repeatingOptionalIdents() throws GrammarError {
        if (next.getTerminal() == Terminals.ENDPROC
                || next.getTerminal() == Terminals.ENDFUN
                || next.getTerminal() == Terminals.ENDWHILE
                || next.getTerminal() == Terminals.ENDIF
                || next.getTerminal() == Terminals.ELSE
                || next.getTerminal() == Terminals.ENDPROGRAM
                || next.getTerminal() == Terminals.SEMICOLON) {
            consume();
        }
        else if (next.getTerminal() == Terminals.COMMA) {
            consume();
            if (next.getTerminal() == Terminals.IDENT) {
                consume();
                idents();
            }  else {
                throwGrammarError();
            }
        }
        else {
            throwGrammarError();
        }
    }

    private void expressionList() throws GrammarError {
        if (next.getTerminal() == Terminals.LPAREN) {
            consume();
            optionalExpressions();
            if (next.getTerminal() == Terminals.RPAREN) {
                consume();
            } else {
                throwGrammarError();
            }
        } else {
            throwGrammarError();
        }
    }

    private void optionalExpressions() throws GrammarError {
        if (next.getTerminal() == Terminals.RPAREN) {
            consume();
            if (next.getTerminal() == Terminals.REAL
                    || next.getTerminal() == Terminals.IMAG
                    || next.getTerminal() == Terminals.LPAREN
                    || next.getTerminal() == Terminals.ADDOPR
                    || next.getTerminal() == Terminals.NOT
                    || next.getTerminal() == Terminals.IDENT
                    || next.getTerminal() == Terminals.LITERAL) {
                consume();
                expression();
                repeatingOptionalExpressions();
            } else {
                throwGrammarError();
            }
        } else {
            throwGrammarError();
        }
    }

    private void repeatingOptionalExpressions() throws GrammarError {
        if (next.getTerminal() == Terminals.RPAREN) {
            consume();
        } else if (next.getTerminal() == Terminals.COMMA) {
            consume();
            expression();
            repeatingOptionalExpressions();
        } else {
            throwGrammarError();
        }
    }

    private void expression() throws GrammarError {
        if (next.getTerminal() == Terminals.REAL
                || next.getTerminal() == Terminals.IMAG
                || next.getTerminal() == Terminals.LPAREN
                || next.getTerminal() == Terminals.ADDOPR
                || next.getTerminal() == Terminals.NOT
                || next.getTerminal() == Terminals.IDENT
                || next.getTerminal() == Terminals.LITERAL) {
            consume();
            term1();
            repBoolOprTerm1();
        } else {
            throwGrammarError();
        }
    }

    private void repBoolOprTerm1() throws GrammarError {
        if (next.getTerminal() == Terminals.RPAREN
                || next.getTerminal() == Terminals.COMMA
                || next.getTerminal() == Terminals.DO
                || next.getTerminal() == Terminals.THEN
                || next.getTerminal() == Terminals.ENDPROC
                || next.getTerminal() == Terminals.ENDFUN
                || next.getTerminal() == Terminals.ENDWHILE
                || next.getTerminal() == Terminals.ENDIF
                || next.getTerminal() == Terminals.ELSE
                || next.getTerminal() == Terminals.ENDPROGRAM
                || next.getTerminal() == Terminals.SEMICOLON
                || next.getTerminal() == Terminals.BECOMES) {
            consume();
        }
        else if(next.getTerminal() == Terminals.BOOLOPR) {
            consume();
            term1();
            repBoolOprTerm1();
        }
        else {
            throwGrammarError();
        }
    }

    private void term1() {

    }

    private void optionalLocalStorageDeclarations() throws GrammarError {
        if (next.getTerminal() == Terminals.DO) {
            consume();
        } else if (next.getTerminal() == Terminals.LOCAL) {
            consume();
            storageDeclaration();
            repeatingOptionalStorageDeclarations();
        } else {
            throwGrammarError();
        }
    }

    private void repeatingOptionalStorageDeclarations() throws GrammarError {
        if (next.getTerminal() == Terminals.DO) {
            consume();
        } else if (next.getTerminal() == Terminals.SEMICOLON) {
            consume();
            storageDeclaration();
            repeatingOptionalStorageDeclarations();
        } else {
            throwGrammarError();
        }
    }

    private void optionalGlobalImports() throws GrammarError {
        if (next.getTerminal() == Terminals.DO) {
            consume();
        } else if (next.getTerminal() == Terminals.LOCAL) {
            consume();
        } else if (next.getTerminal() == Terminals.GLOBAL) {
            consume();
            globalImport();
            repeatingOptionalGlobalImports();
        }
    }

    private void repeatingOptionalGlobalImports() throws GrammarError {
        if (next.getTerminal() == Terminals.DO) {
            consume();
        } else if (next.getTerminal() == Terminals.LOCAL) {
            consume();
        } else if (next.getTerminal() == Terminals.COMMA) {
            consume();
            globalImport();
            repeatingOptionalGlobalImports();
        } else {
            throwGrammarError();
        }
    }

    private void globalImport() throws GrammarError {
        if (next.getTerminal() == Terminals.IDENT) {
            consume();
            optionalFlowMode();
            optionalChangeMode();
            if (next.getTerminal() == Terminals.IDENT) {
                consume();
            } else {
                throwGrammarError();
            }
        } else if (next.getTerminal() == Terminals.CHANGEMODE) {
            consume();
            optionalFlowMode();
            optionalChangeMode();
            if (next.getTerminal() == Terminals.IDENT) {
                consume();
            } else {
                throwGrammarError();
            }
        } else if (next.getTerminal() == Terminals.FLOWMODE) {
            consume();
            optionalFlowMode();
            optionalChangeMode();
            if (next.getTerminal() == Terminals.IDENT) {
                consume();
            } else {
                throwGrammarError();
            }
        }
    }

    private void parameterList() throws GrammarError {
        if (next.getTerminal() == Terminals.LPAREN) {
            consume();
            optionalParameters();
            // TODO: maybe change
            if (next.getTerminal() == Terminals.RPAREN) {
                consume();
            } else {
                throwGrammarError();
            }
        } else {
            throwGrammarError();
        }
    }

    private void optionalParameters() throws GrammarError {
        if (next.getTerminal() == Terminals.RPAREN) {
            consume();
        } else if (next.getTerminal() == Terminals.IDENT) {
            consume();
            parameter();
            repeatingOptionalParameters();
        } else if (next.getTerminal() == Terminals.CHANGEMODE) {
            consume();
            parameter();
            repeatingOptionalParameters();
        } else if (next.getTerminal() == Terminals.MECHMODE) {
            consume();
            parameter();
            repeatingOptionalParameters();
        } else if (next.getTerminal() == Terminals.FLOWMODE) {
            consume();
            parameter();
            repeatingOptionalParameters();
        } else {
            throwGrammarError();
        }
    }

    private void repeatingOptionalParameters() throws GrammarError {
        if (next.getTerminal() == Terminals.RPAREN) {
            consume();
        } else if (next.getTerminal() == Terminals.COMMA) {
            consume();
            parameter();
            repeatingOptionalParameters();
        } else {
            throwGrammarError();
        }
    }

    private void parameter() throws GrammarError {
        if (next.getTerminal() == Terminals.IDENT) {
            consume();
            optionalFlowMode();
            optionalMechMode();
            storageDeclaration();
        } else if (next.getTerminal() == Terminals.CHANGEMODE) {
            consume();
            optionalFlowMode();
            optionalMechMode();
            storageDeclaration();
        } else if (next.getTerminal() == Terminals.MECHMODE) {
            consume();
            optionalFlowMode();
            optionalMechMode();
            storageDeclaration();
        } else if (next.getTerminal() == Terminals.FLOWMODE) {
            consume();
            optionalFlowMode();
            optionalMechMode();
            storageDeclaration();
        }
    }

    private void optionalMechMode() throws GrammarError {
        if (next.getTerminal() == Terminals.IDENT) {
            consume();
        } else if (next.getTerminal() == Terminals.CHANGEMODE) {
            consume();
        } else if (next.getTerminal() == Terminals.MECHMODE) {
            consume();
        } else {
            throwGrammarError();
        }
    }

    private void procedureDeclaration() throws GrammarError {
        if (next.getTerminal() == Terminals.PROC) {
            consume();
            if (next.getTerminal() == Terminals.IDENT) {
                consume();
                parameterList();
                optionalGlobalImports();
                optionalLocalStorageDeclarations();
                if (next.getTerminal() == Terminals.DO) {
                    consume();
                    blockCmd();
                    if (next.getTerminal() == Terminals.ENDPROC) {
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

    /**
     * TODO: output position from Scanner
     *
     * @throws GrammarError
     */
    private void throwGrammarError() throws GrammarError {
        throw new GrammarError(String.format("Not a correct Grammar: %s", this.next.toString()));
    }

    /**
     * line 173
     *
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
        } else if (next.getTerminal() == Terminals.IDENT) {
            optionalFlowMode();
            optionalChangeMode();
            typedIdent();
            repeatingOptionalProgramParameters();
        } else if (next.getTerminal() == Terminals.CHANGEMODE) {
            optionalFlowMode();
            optionalChangeMode();
            typedIdent();
            repeatingOptionalProgramParameters();
        } else if (next.getTerminal() == Terminals.FLOWMODE) {
            optionalFlowMode();
            optionalChangeMode();
            typedIdent();
            repeatingOptionalProgramParameters();
        } else {
            throwGrammarError();
        }
    }

    private void repeatingOptionalProgramParameters() throws GrammarError {
        if (next.getTerminal() == Terminals.RPAREN) {
            consume();
        } else if (next.getTerminal() == Terminals.COMMA) {
            consume();
            optionalFlowMode();
            optionalChangeMode();
            typedIdent();
            repeatingOptionalProgramParameters();
        } else {
            throwGrammarError();
        }
    }

    private void typedIdent() throws GrammarError {
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
    }

    private void typeDeclaration() throws GrammarError {
        if (next.getTerminal() == Terminals.TYPE) {
            consume();
        } else if (next.getTerminal() == Terminals.IDENT) {
            consume();
        } else {
            throwGrammarError();
        }
    }

    private void optionalChangeMode() throws GrammarError {
        if (next.getTerminal() == Terminals.IDENT) {
            consume();
        } else if (next.getTerminal() == Terminals.CHANGEMODE) {
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
        } else if (next.getTerminal() == Terminals.IDENT) {
            consume();
        } else if (next.getTerminal() == Terminals.CHANGEMODE) {
            consume();
        } else if (next.getTerminal() == Terminals.FLOWMODE) {
            consume();
            if (next.getTerminal() == Terminals.FLOWMODE) {
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
     *
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