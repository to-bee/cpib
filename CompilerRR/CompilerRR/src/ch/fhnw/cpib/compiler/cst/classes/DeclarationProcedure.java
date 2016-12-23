package ch.fhnw.cpib.compiler.cst.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;

public class DeclarationProcedure implements IConcSyn.IDeclaration{

	IConcSyn.IProcedureDeclaration procDec;

	public DeclarationProcedure(IProcedureDeclaration procDec) {
		super();
		this.procDec = procDec;
	}

	@Override
	public ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IDeclaration toAbs() {
		return procDec.toAbs();
	}
	
	
}
