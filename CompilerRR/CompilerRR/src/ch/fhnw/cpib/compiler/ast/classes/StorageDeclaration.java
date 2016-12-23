package ch.fhnw.cpib.compiler.ast.classes;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IDeclaration;
import ch.fhnw.cpib.compiler.scanner.Token;

public class StorageDeclaration implements IDeclaration {

	Token changeMode;
	IAbsSyn.ITypedIdent typeIdent;

	public StorageDeclaration(Token changeMode, ITypedIdent typeIdent) {
		super();
		this.changeMode = changeMode;
		this.typeIdent = typeIdent;
	}

}
