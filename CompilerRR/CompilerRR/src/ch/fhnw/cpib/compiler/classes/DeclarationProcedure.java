package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IProcedureDeclaration;

public class DeclarationProcedure implements IConcSyn.IDeclaration{

	IConcSyn.IProcedureDeclaration procDec;

	public DeclarationProcedure(IProcedureDeclaration procDec) {
		super();
		this.procDec = procDec;
	}
	
	
}
