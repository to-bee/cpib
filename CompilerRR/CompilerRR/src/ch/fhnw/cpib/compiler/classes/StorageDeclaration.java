package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IOptionalCHANGEMODE;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.ITypedIdent;

public class StorageDeclaration implements IConcSyn.IStorageDeclaration {

	IConcSyn.IOptionalCHANGEMODE mode;
	IConcSyn.ITypedIdent ident;
	public StorageDeclaration(IOptionalCHANGEMODE mode, ITypedIdent ident) {
		super();
		this.mode = mode;
		this.ident = ident;
	}
	
	
}
