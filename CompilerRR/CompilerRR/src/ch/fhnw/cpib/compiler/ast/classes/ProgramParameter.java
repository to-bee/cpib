package ch.fhnw.cpib.compiler.ast.classes;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ITypedIdent;
import ch.fhnw.cpib.compiler.scanner.Token;

public class ProgramParameter implements IAbsSyn.IProgramParameter{

	Token flowMode;
	Token changeMode;
	IAbsSyn.ITypedIdent typeIdent;
	
	public ProgramParameter(Token flowMode, Token changeMode,
			ITypedIdent typeIdent) {
		super();
		this.flowMode = flowMode;
		this.changeMode = changeMode;
		this.typeIdent = typeIdent;
	}

}
