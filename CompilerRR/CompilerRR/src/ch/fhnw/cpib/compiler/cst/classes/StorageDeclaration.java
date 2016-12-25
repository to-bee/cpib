package ch.fhnw.cpib.compiler.cst.classes;

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
	

	@Override
	public ch.fhnw.cpib.compiler.ast.classes.StorageDeclaration toAbs() {
		return new ch.fhnw.cpib.compiler.ast.classes.StorageDeclaration(
				mode.toAbs(), ident.toAbs());
	}
	
	
}
