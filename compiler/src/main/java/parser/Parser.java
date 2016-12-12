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

    private void term1() throws GrammarError {
        if (next.getTerminal() == Terminals.REAL
                || next.getTerminal() == Terminals.IMAG
                || next.getTerminal() == Terminals.LPAREN
                || next.getTerminal() == Terminals.ADDOPR
                || next.getTerminal() == Terminals.NOT
                || next.getTerminal() == Terminals.IDENT
                || next.getTerminal() == Terminals.LITERAL) {
            consume();
            term2();
            repRelOprTerm2();
        }else {
            throwGrammarError();
        }
    }

    private void repRelOprTerm2() throws GrammarError {
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
                || next.getTerminal() == Terminals.BECOMES
                || next.getTerminal() == Terminals.BOOLOPR) {
            consume();
        }
        else if (next.getTerminal() == Terminals.RELOPR) {
            consume();
            term2();
            repRelOprTerm2();
        } else {
            throwGrammarError();
        }


        }

    private void term2()throws GrammarError {
        if (next.getTerminal() == Terminals.REAL
                || next.getTerminal() == Terminals.IMAG
                || next.getTerminal() == Terminals.LPAREN
                || next.getTerminal() == Terminals.ADDOPR
                || next.getTerminal() == Terminals.NOT
                || next.getTerminal() == Terminals.IDENT
                || next.getTerminal() == Terminals.LITERAL) {
            consume();
            term3();
            repAddOprTerm3();
        }else {
            throwGrammarError();
        }
    }

    private void repAddOprTerm3() throws GrammarError {
        if (next.getTerminal() == Terminals.ADDOPR) {
            consume();
            term3();
            repAddOprTerm3();
        }
        else if (next.getTerminal() == Terminals.RPAREN
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
                || next.getTerminal() == Terminals.BECOMES
                || next.getTerminal() == Terminals.BOOLOPR
                || next.getTerminal() == Terminals.RELOPR) {
            consume();
        }
        else {
            throwGrammarError();
        }
    }

    private void term3() throws GrammarError {
        if (next.getTerminal() == Terminals.REAL
                || next.getTerminal() == Terminals.IMAG
                || next.getTerminal() == Terminals.LPAREN
                || next.getTerminal() == Terminals.ADDOPR
                || next.getTerminal() == Terminals.NOT
                || next.getTerminal() == Terminals.IDENT
                || next.getTerminal() == Terminals.LITERAL) {
            consume();
            factor();
            repMultOprFactor();
        }else {
            throwGrammarError();
        }
    }

    private void repMultOprFactor() throws GrammarError {
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
                || next.getTerminal() == Terminals.BECOMES
                || next.getTerminal() == Terminals.BOOLOPR
                || next.getTerminal() == Terminals.RELOPR
                || next.getTerminal() == Terminals.ADDOPR) {
            consume();
        }
        else if (next.getTerminal() == Terminals.MULTOPR) {
            consume();
            factor();
            repMultOprFactor();
        } else {
            throwGrammarError();
        }
    }

    private void factor() throws GrammarError {
        if (next.getTerminal() == Terminals.LITERAL) {
            consume();
        }
        else if (next.getTerminal() == Terminals.IDENT) {
            consume();
            optionalIdent();
        }
        else if (next.getTerminal() == Terminals.ADDOPR ) {
            consume();
            monadicOperator();
            factor();
        }
        else if (next.getTerminal() == Terminals.NOT ) {
            consume();
            monadicOperator();
            factor();
        }
        else if (next.getTerminal() == Terminals.LPAREN ) {
            consume();
            expression();
            if (next.getTerminal() == Terminals.RPAREN) {
                consume();
            } else {
                throwGrammarError();
            }
        }
        else if (next.getTerminal() == Terminals.IMAG) {
            consume();
            complImag();
        }
        else if (next.getTerminal() == Terminals.REAL) {
            consume();
            complReal();
        }

        else {
            throwGrammarError();
        }
        
    }

    private void complImag() throws GrammarError {
        if (next.getTerminal() == Terminals.IMAG) {
            consume();
            if (next.getTerminal() == Terminals.LPAREN ) {
                consume();
                factor();
                if (next.getTerminal() == Terminals.RPAREN) {
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

    private void complReal() throws GrammarError {
        if (next.getTerminal() == Terminals.REAL) {
            consume();
            if (next.getTerminal() == Terminals.LPAREN) {
                consume();
                factor();
                if (next.getTerminal() == Terminals.RPAREN) {
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

    private void optionalIdent() throws GrammarError {
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
                || next.getTerminal() == Terminals.BECOMES
                || next.getTerminal() == Terminals.BOOLOPR
                || next.getTerminal() == Terminals.RELOPR
                || next.getTerminal() == Terminals.ADDOPR
                || next.getTerminal() == Terminals.MULTOPR
                || next.getTerminal() == Terminals.INIT) {
            consume();
        }
        else if (next.getTerminal() == Terminals.LPAREN) {
            expressionList();

        } else {
            throwGrammarError();
        }
    }

    private void monadicOperator() throws GrammarError {
        if (next.getTerminal() == Terminals.NOT) {
            consume();
        }
        else if (next.getTerminal() == Terminals.ADDOPR) {
            consume();
        } else {
            throwGrammarError();
        }

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

    private ITokenList getPossibleFollowingToken(Terminals terminal, Terminals followingTerminal) throws GrammarError {
        throw new GrammarError(String.format("%s cannot follow to %s", terminal, followingTerminal));
    }


}