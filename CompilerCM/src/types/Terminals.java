package types;

public enum Terminals {
	/** Basic */
	CALL("CALL"),
	CONST("CONST"),
	COPY("COPY"),
	DEBUGIN("DEBUGIN"),
	DEBUGOUT("DEBUGOUT"),
	DIVE("DIVE"),
	DO("DO"),
	ELSE("ELSE"),
	ENDFUN("ENDFUN"),
	ENDIF("ENDIF"),
	ENDPROC("ENDPROC"),
	ENDPROGRAM("ENDPROGRAM"),
	ENDWHILE("ENDWHILE"),
	FALSE("FALSE"),
	FUN("FUN"),
	GLOBAL("GLOBAL"),
	IF("IF"),
	IN("IN"),
	INIT("INIT"),
	INOUT("INOUT"),
	MODE("MODE"),
	NOT("NOT"),
	OUT("OUT"),
	PROC("PROC"),
	PROGRAM("PROGRAM"),
	REF("REF"),
	RETURNS("RETURNS"),
	SKIP("SKIP"),
	THEN("THEN"),
	TRUE("TRUE"),
	VAR("VAR"),
	WHILE("WHILE"),
	
	SENTINEL("SENTINEL"), 
	LITERAL("LITERAL"),
	IDENT("IDENT"),
	 
	 /** Symbols */
	LPAREN("LPAREN"),
	RPAREN("RPAREN"),
	COMMA("COMMA"),
	SEMICOLON("SEMICOLON"),
	BECOMES("BECOMES"),
	MULTOPR("MULTOPR"),
	ADDOPR("ADDOPR"),
	RELOPR("RELOPR"),
	BOOLOPR("BOOLOPR"),
	COLON("COLON"),
	EQUAL("EQUAL"),
	
	/** Datatype */
	TYPE("TYPE");
	
	private String terminal;

	Terminals(String terminal) {
		this.terminal = terminal;
	}

	public String toString() {
		return terminal;
	}
}
