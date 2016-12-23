package ch.fhnw.cpib.compiler.ast.classes;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IGlobalImport;
import ch.fhnw.cpib.compiler.scanner.Token;

public class GlobalImport implements IGlobalImport {
	Token flowMode;
	Token changeMode;
	Token ident;

	public GlobalImport(Token flowMode, Token changeMode, Token ident) {
		super();
		this.flowMode = flowMode;
		this.changeMode = changeMode;
		this.ident = ident;
	}

}
