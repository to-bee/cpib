package ch.fhnw.cpib.compiler.cst.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IOptionalCHANGEMODE;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IOptionalFLOWMODE;
import ch.fhnw.cpib.compiler.scanner.Token;

public class GlobalImport implements IConcSyn.IGlobalImport {

	IConcSyn.IOptionalFLOWMODE flowmode;
	IConcSyn.IOptionalCHANGEMODE changemode;
	Token ident;
	public GlobalImport(IOptionalFLOWMODE flowmode, IOptionalCHANGEMODE changemode, Token ident) {
		super();
		this.flowmode = flowmode;
		this.changemode = changemode;
		this.ident = ident;
	}
	@Override
	public ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IGlobalImport toAbs() {
		return new ch.fhnw.cpib.compiler.ast.classes.GlobalImport(flowmode.toAbs(), changemode.toAbs(), ident);
	}
	
	
}
