package ch.fhnw.cpib.compiler.ast.classes;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IDeclaration;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IParameter;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IStorageDeclaration;
import ch.fhnw.cpib.compiler.scanner.Token;

public class Parameter implements IParameter {
	Token flowMode;
	Token mechMode;
	IDeclaration storageDeclaration;

	public Parameter(Token flowMode, Token mechMode, IDeclaration storageDeclaration2) {
		super();
		this.flowMode = flowMode;
		this.mechMode = mechMode;
		this.storageDeclaration = storageDeclaration2;
	}

}
