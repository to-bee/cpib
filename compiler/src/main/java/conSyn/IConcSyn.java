package conSyn;

import scanner.errors.GrammarError;

public interface IConcSyn {
    void parse() throws GrammarError;

//    void optionalGlobalDeclarations() throws GrammarError;
//
//    void declarations() throws GrammarError;
//
//    void declaration() throws GrammarError;
//
//    void repeatingOptionalDeclarations() throws GrammarError;
//
//    void functionDeclaration() throws GrammarError;
//
//    void blockCmd() throws GrammarError;
//
//    void repeatingOptionalCmds() throws GrammarError;
//
//    void cmd() throws GrammarError;
//
//    void optionalGlobalInits() throws GrammarError;
//
//    void idents() throws GrammarError;
//
//    void repeatingOptionalIdents() throws GrammarError;
//
//    void expressionList() throws GrammarError;
//
//    void optionalExpressions() throws GrammarError;
//
//    void repeatingOptionalExpressions() throws GrammarError;
//
//    void expression() throws GrammarError;
//
//    void repBoolOprTerm1() throws GrammarError;
//
//    void term1() throws GrammarError;
//
//    void repRelOprTerm2() throws GrammarError;
//
//    void term2() throws GrammarError;
//
//    void repAddOprTerm3() throws GrammarError;
//
//    void term3() throws GrammarError;
//
//    void repMultOprFactor() throws GrammarError;
//
//    void factor() throws GrammarError;
//
//    void complImag() throws GrammarError;
//
//    void complReal() throws GrammarError;
//
//    void optionalIdent() throws GrammarError;
//
//    void monadicOperator() throws GrammarError;
//
//    void optionalLocalStorageDeclarations() throws GrammarError;
//
//    void repeatingOptionalStorageDeclarations() throws GrammarError;
//
//    void optionalGlobalImports() throws GrammarError;
//
//    void repeatingOptionalGlobalImports() throws GrammarError;
//
//    void globalImport() throws GrammarError;
//
//    void parameterList() throws GrammarError;
//
//    void optionalParameters() throws GrammarError;
//
//    void repeatingOptionalParameters() throws GrammarError;
//
//    void parameter() throws GrammarError;
//
//    void storageDeclaration() throws GrammarError;
//
//    void procedureDeclaration() throws GrammarError;
//
//    void throwGrammarError() throws GrammarError;
//
//    void programParameterList() throws GrammarError;
//
//    void optionalProgramParam() throws GrammarError;
//
//    void repeatingOptionalProgramParameters() throws GrammarError;
//
//    void typedIdent() throws GrammarError;
//
//    void typeDeclaration() throws GrammarError;
//
//    void optionalChangeMode() throws GrammarError;
//
//    void parseNext(new OptionalFlowMode(getTokenList())); throws GrammarError;
//
//    void optionalMechMode() throws GrammarError;
//
//    void consume() throws GrammarError;
//
//    void consumeWhile(IToken token) throws GrammarError;
//
//    void consumeIdent(IToken token) throws GrammarError;
//
//    void consumeRelopr(IToken token) throws GrammarError;
//
//    void consumeSentinel(IToken last) throws GrammarError;
//
//    ITokenList getPossibleFollowingToken(Terminal terminal, Terminal followingTerminal) throws GrammarError;
}
