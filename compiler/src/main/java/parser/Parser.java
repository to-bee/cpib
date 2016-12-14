package parser;

import conSyn.ConcSyn;
import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.datatypes.TerminalType;
import scanner.errors.GrammarError;
import scanner.token.IToken;
import scanner.tokenList.ITokenList;
import scanner.tokenList.TokenList;

/**
 * Created by tobi on 27/09/16.
 */
public class Parser implements IParser {

    private final ITokenList tokenList;
    private final ITokenList tokenListDone;
    private final IConcSyn concSyn;
    private IToken next;

    public Parser(ITokenList tokenList) {
        this.tokenList = tokenList.clone();
        this.tokenListDone = new TokenList();
        this.concSyn = new ConcSyn();
    }

    @Override
    public IConcSyn parseProgram() throws GrammarError {
        // loads first token and checks if it starts with program
        next = tokenList.nextToken();
        if (next.getTerminal() == Terminal.PROGRAM) {
            // consume program
            consume();
            // check if netxt token is identifier
            if (next.getTerminal() == Terminal.IDENT) {
                consume();
                programParameterList();
                optionalGlobalDeclarations();
                if (next.getTerminal() == Terminal.DO) {
                    consume();
                    blockCmd();
                    if (next.getTerminal() == Terminal.ENDPROGRAM) {
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
        if (next.getTerminal() == Terminal.DO) {
            consume();
        } else if (next.getTerminal() == Terminal.GLOBAL) {
            consume();
            declarations();
        } else {
            throwGrammarError();
        }
    }

    private void declarations() throws GrammarError {
        if (next.getTerminal() == Terminal.IDENT
                || next.getTerminal() == Terminal.FUN
                || next.getTerminal() == Terminal.PROC
                || next.getTerminal().getType() == TerminalType.CHANGEMODE) {
            declaration();
            repeatingOptionalDeclarations();
        } else {
            throwGrammarError();
        }
    }

    private void declaration() throws GrammarError {
        if (next.getTerminal() == Terminal.IDENT
                || next.getTerminal().getType() == TerminalType.CHANGEMODE) {
            storageDeclaration();
        } else if (next.getTerminal() == Terminal.FUN) {
            functionDeclaration();
        } else if (next.getTerminal() == Terminal.PROC) {
            procedureDeclaration();
        } else {
            throwGrammarError();
        }
    }

    private void repeatingOptionalDeclarations() throws GrammarError {
        if (next.getTerminal() == Terminal.DO) {

        } else if (next.getTerminal() == Terminal.SEMICOLON) {
            consume();
            declaration();
            repeatingOptionalDeclarations();
        } else {
            throwGrammarError();
        }
    }

    private void functionDeclaration() throws GrammarError {
        if (next.getTerminal() == Terminal.FUN) {
            consume();
            if (next.getTerminal() == Terminal.IDENT) {
                consume();
                parameterList();
                if (next.getTerminal() == Terminal.RETURNS) {
                    consume();
                    storageDeclaration();
                    optionalGlobalImports();
                    optionalLocalStorageDeclarations();
                    if (next.getTerminal() == Terminal.DO) {
                        consume();
                        blockCmd();
                        if (next.getTerminal() == Terminal.ENDFUN) {
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
        if (next.getTerminal() == Terminal.DEBUGOUT
                || next.getTerminal() == Terminal.DEBUGIN
                || next.getTerminal() == Terminal.CALL
                || next.getTerminal() == Terminal.WHILE
                || next.getTerminal() == Terminal.IF
                || next.getTerminal() == Terminal.REAL
                || next.getTerminal() == Terminal.IMAG
                || next.getTerminal() == Terminal.LPAREN
                || next.getTerminal() == Terminal.ADDOPR
                || next.getTerminal() == Terminal.NOT
                || next.getTerminal() == Terminal.IDENT
                || next.getTerminal() == Terminal.LITERAL
                || next.getTerminal() == Terminal.SKIP) {
            cmd();
            repeatingOptionalCmds();
        } else {
            throwGrammarError();
        }
    }

    private void repeatingOptionalCmds() throws GrammarError {
        if (next.getTerminal() == Terminal.ENDPROC
                || next.getTerminal() == Terminal.ENDFUN
                || next.getTerminal() == Terminal.ENDWHILE
                || next.getTerminal() == Terminal.ENDIF
                || next.getTerminal() == Terminal.ELSE
                || next.getTerminal() == Terminal.ENDPROGRAM) {

        } else if (next.getTerminal() == Terminal.SEMICOLON) {
            consume();
            cmd();
            repeatingOptionalCmds();
        } else {
            throwGrammarError();
        }
    }

    private void cmd() throws GrammarError {
        if (next.getTerminal() == Terminal.SKIP) {
            consume();
        } else if (next.getTerminal() == Terminal.REAL
                || next.getTerminal() == Terminal.IMAG
                || next.getTerminal() == Terminal.LPAREN
                || next.getTerminal() == Terminal.ADDOPR
                || next.getTerminal() == Terminal.NOT
                || next.getTerminal() == Terminal.IDENT
                || next.getTerminal() == Terminal.LITERAL) {
            expression();
            if (next.getTerminal() == Terminal.BECOMES) {
                consume();
                expression();
            } else {
                throwGrammarError();
            }
        } else if (next.getTerminal() == Terminal.IF) {
            consume();
            expression();
            if (next.getTerminal() == Terminal.THEN) {
                consume();
                blockCmd();
                if (next.getTerminal() == Terminal.ELSE) {
                    consume();
                    blockCmd();
                    if (next.getTerminal() == Terminal.ENDIF) {
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
        } else if (next.getTerminal() == Terminal.WHILE) {
            consume();
            expression();
            if (next.getTerminal() == Terminal.DO) {
                consume();
                blockCmd();
                if (next.getTerminal() == Terminal.ENDWHILE) {
                    consume();
                } else {
                    throwGrammarError();
                }
            } else {
                throwGrammarError();
            }
        } else if (next.getTerminal() == Terminal.CALL) {
            consume();
            if (next.getTerminal() == Terminal.IDENT) {
                consume();
                expressionList();
                optionalGlobalInits();
            } else {
                throwGrammarError();
            }
        } else if (next.getTerminal() == Terminal.DEBUGIN
                || next.getTerminal() == Terminal.DEBUGOUT) {
            consume();
            expression();
        } else {
            throwGrammarError();
        }
    }

    private void optionalGlobalInits() throws GrammarError {
        if (next.getTerminal() == Terminal.ENDPROC
                || next.getTerminal() == Terminal.ENDFUN
                || next.getTerminal() == Terminal.ENDWHILE
                || next.getTerminal() == Terminal.ENDIF
                || next.getTerminal() == Terminal.ELSE
                || next.getTerminal() == Terminal.ENDPROGRAM
                || next.getTerminal() == Terminal.SEMICOLON) {

        } else if (next.getTerminal() == Terminal.INIT) {
            consume();
            idents();
        } else {
            throwGrammarError();
        }
    }

    private void idents() throws GrammarError {
        if (next.getTerminal() == Terminal.SKIP) {
            consume();
            repeatingOptionalIdents();
        } else {
            throwGrammarError();
        }
    }

    private void repeatingOptionalIdents() throws GrammarError {
        if (next.getTerminal() == Terminal.ENDPROC
                || next.getTerminal() == Terminal.ENDFUN
                || next.getTerminal() == Terminal.ENDWHILE
                || next.getTerminal() == Terminal.ENDIF
                || next.getTerminal() == Terminal.ELSE
                || next.getTerminal() == Terminal.ENDPROGRAM
                || next.getTerminal() == Terminal.SEMICOLON) {
            consume();
        } else if (next.getTerminal() == Terminal.COMMA) {
            consume();
            if (next.getTerminal() == Terminal.IDENT) {
                consume();
                idents();
            } else {
                throwGrammarError();
            }
        } else {
            throwGrammarError();
        }
    }

    private void expressionList() throws GrammarError {
        if (next.getTerminal() == Terminal.LPAREN) {
            consume();
            optionalExpressions();
            if (next.getTerminal() == Terminal.RPAREN) {
                consume();
            } else {
                throwGrammarError();
            }
        } else {
            throwGrammarError();
        }
    }

    private void optionalExpressions() throws GrammarError {
        if (next.getTerminal() == Terminal.RPAREN) {

        } else if (next.getTerminal() == Terminal.REAL
                || next.getTerminal() == Terminal.IMAG
                || next.getTerminal() == Terminal.LPAREN
                || next.getTerminal() == Terminal.ADDOPR
                || next.getTerminal() == Terminal.NOT
                || next.getTerminal() == Terminal.IDENT
                || next.getTerminal() == Terminal.LITERAL) {
            expression();
            repeatingOptionalExpressions();
        } else {
            throwGrammarError();
        }
    }

    private void repeatingOptionalExpressions() throws GrammarError {
        if (next.getTerminal() == Terminal.RPAREN) {
            consume();
        } else if (next.getTerminal() == Terminal.COMMA) {
            consume();
            expression();
            repeatingOptionalExpressions();
        } else {
            throwGrammarError();
        }
    }

    private void expression() throws GrammarError {
        if (next.getTerminal() == Terminal.REAL
                || next.getTerminal() == Terminal.IMAG
                || next.getTerminal() == Terminal.LPAREN
                || next.getTerminal() == Terminal.ADDOPR
                || next.getTerminal() == Terminal.NOT
                || next.getTerminal() == Terminal.IDENT
                || next.getTerminal() == Terminal.LITERAL) {
            term1();
            repBoolOprTerm1();
        } else {
            throwGrammarError();
        }
    }

    private void repBoolOprTerm1() throws GrammarError {
        if (next.getTerminal() == Terminal.RPAREN
                || next.getTerminal() == Terminal.COMMA
                || next.getTerminal() == Terminal.DO
                || next.getTerminal() == Terminal.THEN
                || next.getTerminal() == Terminal.ENDPROC
                || next.getTerminal() == Terminal.ENDFUN
                || next.getTerminal() == Terminal.ENDWHILE
                || next.getTerminal() == Terminal.ENDIF
                || next.getTerminal() == Terminal.ELSE
                || next.getTerminal() == Terminal.ENDPROGRAM
                || next.getTerminal() == Terminal.SEMICOLON
                || next.getTerminal() == Terminal.BECOMES) {

        } else if (next.getTerminal().getType() == TerminalType.BOOLOPR) {
            consume();
            term1();
            repBoolOprTerm1();
        } else {
            throwGrammarError();
        }
    }

    private void term1() throws GrammarError {
        if (next.getTerminal() == Terminal.REAL
                || next.getTerminal() == Terminal.IMAG
                || next.getTerminal() == Terminal.LPAREN
                || next.getTerminal() == Terminal.ADDOPR
                || next.getTerminal() == Terminal.NOT
                || next.getTerminal() == Terminal.IDENT
                || next.getTerminal() == Terminal.LITERAL) {
            term2();
            repRelOprTerm2();
        } else {
            throwGrammarError();
        }
    }

    private void repRelOprTerm2() throws GrammarError {
        if (next.getTerminal() == Terminal.RPAREN
                || next.getTerminal() == Terminal.COMMA
                || next.getTerminal() == Terminal.DO
                || next.getTerminal() == Terminal.THEN
                || next.getTerminal() == Terminal.ENDPROC
                || next.getTerminal() == Terminal.ENDFUN
                || next.getTerminal() == Terminal.ENDWHILE
                || next.getTerminal() == Terminal.ENDIF
                || next.getTerminal() == Terminal.ELSE
                || next.getTerminal() == Terminal.ENDPROGRAM
                || next.getTerminal() == Terminal.SEMICOLON
                || next.getTerminal() == Terminal.BECOMES
                || next.getTerminal().getType() == TerminalType.BOOLOPR) {

        } else if (next.getTerminal().getType() == TerminalType.RELOPR) {
            consume();
            term2();
            repRelOprTerm2();
        } else {
            throwGrammarError();
        }


    }

    private void term2() throws GrammarError {
        if (next.getTerminal() == Terminal.REAL
                || next.getTerminal() == Terminal.IMAG
                || next.getTerminal() == Terminal.LPAREN
                || next.getTerminal() == Terminal.ADDOPR
                || next.getTerminal() == Terminal.NOT
                || next.getTerminal() == Terminal.IDENT
                || next.getTerminal() == Terminal.LITERAL) {
            term3();
            repAddOprTerm3();
        } else {
            throwGrammarError();
        }
    }

    private void repAddOprTerm3() throws GrammarError {
        if (next.getTerminal() == Terminal.ADDOPR) {
            consume();
            term3();
            repAddOprTerm3();
        } else if (next.getTerminal() == Terminal.RPAREN
                || next.getTerminal() == Terminal.COMMA
                || next.getTerminal() == Terminal.DO
                || next.getTerminal() == Terminal.THEN
                || next.getTerminal() == Terminal.ENDPROC
                || next.getTerminal() == Terminal.ENDFUN
                || next.getTerminal() == Terminal.ENDWHILE
                || next.getTerminal() == Terminal.ENDIF
                || next.getTerminal() == Terminal.ELSE
                || next.getTerminal() == Terminal.ENDPROGRAM
                || next.getTerminal() == Terminal.SEMICOLON
                || next.getTerminal() == Terminal.BECOMES
                || next.getTerminal().getType() == TerminalType.BOOLOPR
                || next.getTerminal().getType() == TerminalType.RELOPR) {

        } else {
            throwGrammarError();
        }
    }

    private void term3() throws GrammarError {
        if (next.getTerminal() == Terminal.REAL
                || next.getTerminal() == Terminal.IMAG
                || next.getTerminal() == Terminal.LPAREN
                || next.getTerminal() == Terminal.ADDOPR
                || next.getTerminal() == Terminal.NOT
                || next.getTerminal() == Terminal.IDENT
                || next.getTerminal() == Terminal.LITERAL) {
            factor();
            repMultOprFactor();
        } else {
            throwGrammarError();
        }
    }

    private void repMultOprFactor() throws GrammarError {
        if (next.getTerminal() == Terminal.RPAREN
                || next.getTerminal() == Terminal.COMMA
                || next.getTerminal() == Terminal.DO
                || next.getTerminal() == Terminal.THEN
                || next.getTerminal() == Terminal.ENDPROC
                || next.getTerminal() == Terminal.ENDFUN
                || next.getTerminal() == Terminal.ENDWHILE
                || next.getTerminal() == Terminal.ENDIF
                || next.getTerminal() == Terminal.ELSE
                || next.getTerminal() == Terminal.ENDPROGRAM
                || next.getTerminal() == Terminal.SEMICOLON
                || next.getTerminal() == Terminal.BECOMES
                || next.getTerminal().getType() == TerminalType.BOOLOPR
                || next.getTerminal().getType() == TerminalType.RELOPR
                || next.getTerminal() == Terminal.ADDOPR) {

        } else if (next.getTerminal() == Terminal.MULTOPR) {
            consume();
            factor();
            repMultOprFactor();
        } else {
            throwGrammarError();
        }
    }

    private void factor() throws GrammarError {
        if (next.getTerminal() == Terminal.LITERAL) {
            consume();
        } else if (next.getTerminal() == Terminal.IDENT) {
            consume();
            optionalIdent();
        } else if (next.getTerminal() == Terminal.ADDOPR) {
            monadicOperator();
            factor();
        } else if (next.getTerminal() == Terminal.NOT) {
            monadicOperator();
            factor();
        } else if (next.getTerminal() == Terminal.LPAREN) {
            consume();
            expression();
            if (next.getTerminal() == Terminal.RPAREN) {
                consume();
            } else {
                throwGrammarError();
            }
        } else if (next.getTerminal() == Terminal.IMAG) {
            complImag();
        } else if (next.getTerminal() == Terminal.REAL) {
            complReal();
        } else {
            throwGrammarError();
        }

    }

    private void complImag() throws GrammarError {
        if (next.getTerminal() == Terminal.IMAG) {
            consume();
            if (next.getTerminal() == Terminal.LPAREN) {
                consume();
                factor();
                if (next.getTerminal() == Terminal.RPAREN) {
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
        if (next.getTerminal() == Terminal.REAL) {
            consume();
            if (next.getTerminal() == Terminal.LPAREN) {
                consume();
                factor();
                if (next.getTerminal() == Terminal.RPAREN) {
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
        if (next.getTerminal() == Terminal.RPAREN
                || next.getTerminal() == Terminal.COMMA
                || next.getTerminal() == Terminal.DO
                || next.getTerminal() == Terminal.THEN
                || next.getTerminal() == Terminal.ENDPROC
                || next.getTerminal() == Terminal.ENDFUN
                || next.getTerminal() == Terminal.ENDWHILE
                || next.getTerminal() == Terminal.ENDIF
                || next.getTerminal() == Terminal.ELSE
                || next.getTerminal() == Terminal.ENDPROGRAM
                || next.getTerminal() == Terminal.SEMICOLON
                || next.getTerminal() == Terminal.BECOMES
                || next.getTerminal().getType() == TerminalType.BOOLOPR
                || next.getTerminal().getType() == TerminalType.RELOPR
                || next.getTerminal() == Terminal.ADDOPR
                || next.getTerminal() == Terminal.MULTOPR) {

        } else if (next.getTerminal() == Terminal.INIT) {
            consume();
        } else if (next.getTerminal() == Terminal.LPAREN) {
            expressionList();

        } else {
            throwGrammarError();
        }
    }

    private void monadicOperator() throws GrammarError {
        if (next.getTerminal() == Terminal.NOT || next.getTerminal() == Terminal.ADDOPR) {
            consume();
        } else {
            throwGrammarError();
        }

    }

    /**
     * Optional: No grammar error
     *
     * @throws GrammarError
     */
    private void optionalLocalStorageDeclarations() throws GrammarError {
        if (next.getTerminal() == Terminal.DO) {

        } else if (next.getTerminal() == Terminal.LOCAL) {
            consume();
            storageDeclaration();
            repeatingOptionalStorageDeclarations();
        }
    }

    private void repeatingOptionalStorageDeclarations() throws GrammarError {
        if (next.getTerminal() == Terminal.DO) {

        } else if (next.getTerminal() == Terminal.SEMICOLON) {
            consume();
            storageDeclaration();
            repeatingOptionalStorageDeclarations();
        } else {
            throwGrammarError();
        }
    }

    private void optionalGlobalImports() throws GrammarError {
        if (next.getTerminal() == Terminal.DO) {
//            consume();
        } else if (next.getTerminal() == Terminal.LOCAL) {
//            consume();
        } else if (next.getTerminal() == Terminal.GLOBAL) {
            consume();
            globalImport();
            repeatingOptionalGlobalImports();
        }
    }

    private void repeatingOptionalGlobalImports() throws GrammarError {
        if (next.getTerminal() == Terminal.DO) {
            consume();
        } else if (next.getTerminal() == Terminal.LOCAL) {
            consume();
        } else if (next.getTerminal() == Terminal.COMMA) {
            consume();
            globalImport();
            repeatingOptionalGlobalImports();
        } else {
            throwGrammarError();
        }
    }

    private void globalImport() throws GrammarError {
        if (next.getTerminal() == Terminal.IDENT) {
            consume();
            optionalFlowMode();
            optionalChangeMode();
            if (next.getTerminal() == Terminal.IDENT) {
                consume();
            } else {
                throwGrammarError();
            }
        } else if (next.getTerminal().getType() == TerminalType.CHANGEMODE) {
            consume();
            optionalFlowMode();
            optionalChangeMode();
            if (next.getTerminal() == Terminal.IDENT) {
                consume();
            } else {
                throwGrammarError();
            }
        } else if (next.getTerminal().getType() == TerminalType.FLOWMODE) {
            consume();
            optionalFlowMode();
            optionalChangeMode();
            if (next.getTerminal() == Terminal.IDENT) {
                consume();
            } else {
                throwGrammarError();
            }
        }
    }

    private void parameterList() throws GrammarError {
        if (next.getTerminal() == Terminal.LPAREN) {
            consume();
            optionalParameters();
            if (next.getTerminal() == Terminal.RPAREN) {
                consume();
            } else {
                throwGrammarError();
            }
        } else {
            throwGrammarError();
        }
    }

    private void optionalParameters() throws GrammarError {
        if (next.getTerminal() == Terminal.RPAREN) {

        } else if (next.getTerminal() == Terminal.IDENT
                || next.getTerminal().getType() == TerminalType.CHANGEMODE
                || next.getTerminal().getType() == TerminalType.MECHMODE
                || next.getTerminal().getType() == TerminalType.FLOWMODE) {
            parameter();
            repeatingOptionalParameters();
        } else {
            throwGrammarError();
        }
    }

    private void repeatingOptionalParameters() throws GrammarError {
        if (next.getTerminal() == Terminal.RPAREN) {

        } else if (next.getTerminal() == Terminal.COMMA) {
            consume();
            parameter();
            repeatingOptionalParameters();
        } else {
            throwGrammarError();
        }
    }

    private void parameter() throws GrammarError {
        if (next.getTerminal() == Terminal.IDENT
                || next.getTerminal().getType() == TerminalType.CHANGEMODE
                || next.getTerminal().getType() == TerminalType.MECHMODE
                || next.getTerminal().getType() == TerminalType.FLOWMODE) {
            optionalFlowMode();
            optionalMechMode();
            storageDeclaration();
        } else {
            throwGrammarError();
        }
    }

    private void storageDeclaration() throws GrammarError {
        if (next.getTerminal() == Terminal.IDENT
                || next.getTerminal().getType() == TerminalType.CHANGEMODE) {
            optionalChangeMode();
            typedIdent();
        } else {
            // TODO: type zuweisung nicht unterstützt?
            throwGrammarError();
        }
    }

    private void procedureDeclaration() throws GrammarError {
        if (next.getTerminal() == Terminal.PROC) {
            consume();
            if (next.getTerminal() == Terminal.IDENT) {
                consume();
                parameterList();
                optionalGlobalImports();
                optionalLocalStorageDeclarations();
                if (next.getTerminal() == Terminal.DO) {
                    consume();
                    blockCmd();
                    if (next.getTerminal() == Terminal.ENDPROC) {
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
     * TODO: output position from scanner.Scanner
     *
     * @throws GrammarError
     */
    private void throwGrammarError() throws GrammarError {
        throw new GrammarError(String.format("%s: Could not parse grammar", this.next.toString()));
    }

    /**
     * line 173
     *
     * @throws GrammarError
     */
    private void programParameterList() throws GrammarError {
        if (next.getTerminal() == Terminal.LPAREN) {
            consume();
            optionalProgramParam();
        } else {
            throwGrammarError();
        }
    }

    private void optionalProgramParam() throws GrammarError {
        if (next.getTerminal() == Terminal.RPAREN) {
            consume();
        } else if (next.getTerminal() == Terminal.IDENT) {
            optionalFlowMode();
            optionalChangeMode();
            typedIdent();
            repeatingOptionalProgramParameters();
        } else if (next.getTerminal().getType() == TerminalType.CHANGEMODE) {
            optionalFlowMode();
            optionalChangeMode();
            typedIdent();
            repeatingOptionalProgramParameters();
        } else if (next.getTerminal().getType() == TerminalType.FLOWMODE) {
            optionalFlowMode();
            optionalChangeMode();
            typedIdent();
            repeatingOptionalProgramParameters();
        } else {
            throwGrammarError();
        }
    }

    /**
     * Needs a throw grammar error part because at least RPAREN is required
     *
     * @throws GrammarError
     */
    private void repeatingOptionalProgramParameters() throws GrammarError {
        if (next.getTerminal() == Terminal.RPAREN) {
            consume();
        } else if (next.getTerminal() == Terminal.COMMA) {
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
        if (next.getTerminal() == Terminal.IDENT) {
            consume();
            if (next.getTerminal() == Terminal.COLON) {
                consume();
                typeDeclaration();
            }
        } else {
            throwGrammarError();
        }
    }

    private void typeDeclaration() throws GrammarError {
        if (next.getTerminal().getType() == TerminalType.TYPE
                || next.getTerminal() == Terminal.IDENT) {
            consume();
        } else {
            throwGrammarError();
        }
    }

    /**
     * @throws GrammarError
     */
    private void optionalChangeMode() throws GrammarError {
        if (next.getTerminal() == Terminal.IDENT) {

        } else if (next.getTerminal().getType() == TerminalType.CHANGEMODE) {
            consume();
        } else {
            throwGrammarError();
        }
    }

    /**
     * @throws GrammarError
     */
    private void optionalFlowMode() throws GrammarError {
        if (next.getTerminal() == Terminal.IDENT
                || next.getTerminal().getType() == TerminalType.MECHMODE
                || next.getTerminal().getType() == TerminalType.CHANGEMODE) {

        } else if (next.getTerminal().getType() == TerminalType.FLOWMODE) {
            consume();
        } else {
            throwGrammarError();
        }
    }

    /**
     * @throws GrammarError
     */
    private void optionalMechMode() throws GrammarError {
        if (next.getTerminal() == Terminal.IDENT
                || next.getTerminal().getType() == TerminalType.CHANGEMODE) {

        } else if (next.getTerminal().getType() == TerminalType.MECHMODE) {
            consume();
        }
    }

    /**
     * Loads the next token
     *
     * @throws GrammarError
     */
    public void consume() throws GrammarError {
        next = tokenList.nextToken();
        tokenListDone.add(next);
    }

    public void consumeWhile(IToken token) throws GrammarError {
        this.concSyn.add(token);
        IToken next = tokenList.nextToken();
        if (next.getTerminal() == Terminal.IDENT) {
            consumeIdent(next);
        } else {
            throw new GrammarError("Not a correct Grammar: line xy, token: xy");
        }
    }

    private void consumeIdent(IToken token) throws GrammarError {
        this.concSyn.add(token);
        IToken next = tokenList.nextToken();
        if (next.getTerminal().getType() == TerminalType.RELOPR) {
            consumeRelopr(next);
        } else {
            throw new GrammarError("Not a correct Grammar: line xy, token: xy");
        }
    }

    private void consumeRelopr(IToken token) throws GrammarError {
        this.concSyn.add(token);
        IToken next = tokenList.nextToken();
        if (next.getTerminal() == Terminal.SENTINEL) {
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
            throw new GrammarError(String.format("%s must be the last token", Terminal.SENTINEL));
        } else {
            throw new GrammarError("Not a correct Grammar: line xy, token: xy");
        }
    }

    private ITokenList getPossibleFollowingToken(Terminal terminal, Terminal followingTerminal) throws GrammarError {
        throw new GrammarError(String.format("%s cannot follow to %s", terminal, followingTerminal));
    }


}