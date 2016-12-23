package ch.fhnw.cpib.compiler.cst.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;

public class DeclarationFunction implements IConcSyn.IDeclaration{

	IConcSyn.IFunctionDeclaration funcDec;

	public DeclarationFunction(IFunctionDeclaration funcDec) {
		super();
		this.funcDec = funcDec;
	}

	@Override
	public ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IDeclaration toAbs() {
		return funcDec.toAbs();
	}


	
}
