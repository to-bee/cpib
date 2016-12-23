package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IStorageDeclaration;

public class DeclarationStorage implements IConcSyn.IDeclaration {

	IConcSyn.IStorageDeclaration storDec;

	public DeclarationStorage(IStorageDeclaration storDec) {
		super();
		this.storDec = storDec;
	}
	
}
