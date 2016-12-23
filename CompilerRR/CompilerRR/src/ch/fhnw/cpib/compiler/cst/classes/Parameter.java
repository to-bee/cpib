package ch.fhnw.cpib.compiler.cst.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IOptionalFLOWMODE;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IOptionalMECHMODE;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IStorageDeclaration;

public class Parameter implements IConcSyn.IParameter  {

	IConcSyn.IOptionalFLOWMODE flowMode;
	IConcSyn.IOptionalMECHMODE mechMode;
	IConcSyn.IStorageDeclaration stoDecl;
	public Parameter(IOptionalFLOWMODE flowMode, IOptionalMECHMODE mechMode, IStorageDeclaration stoDecl) {
		super();
		this.flowMode = flowMode;
		this.mechMode = mechMode;
		this.stoDecl = stoDecl;
	}
	
	@Override
	public ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IParameter toAbs() {
		return new ch.fhnw.cpib.compiler.ast.classes.Parameter(flowMode.toAbs(), mechMode.toAbs(), stoDecl.toAbs());
	}
	
	
}
