package ch.fhnw.cpib.compiler.ast.classes;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn;

public class DeclarationStorage implements IAbsSyn.IStorageDeclaration {

	IStorageDeclaration sd;

	public DeclarationStorage(IStorageDeclaration sd) {
		super();
		this.sd = sd;
	}
	
	
}
