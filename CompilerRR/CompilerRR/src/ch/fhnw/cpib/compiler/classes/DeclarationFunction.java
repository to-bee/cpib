package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IFunctionDeclaration;

public class DeclarationFunction implements IConcSyn.IDeclaration{

	IConcSyn.IFunctionDeclaration funcDec;

	public DeclarationFunction(IFunctionDeclaration funcDec) {
		super();
		this.funcDec = funcDec;
	}
	
	
}
