package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.scanner.Token;

public class OptionalFlowmode implements IConcSyn.IOptionalFLOWMODE{

	Token flowmode;

	public OptionalFlowmode(Token flowmode) {
		super();
		this.flowmode = flowmode;
	}
	
	
}
