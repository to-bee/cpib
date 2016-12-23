package ch.fhnw.cpib.compiler.classes;

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
	
	
}
