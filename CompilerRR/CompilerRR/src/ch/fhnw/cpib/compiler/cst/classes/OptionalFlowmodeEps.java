package ch.fhnw.cpib.compiler.cst.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Operators;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class OptionalFlowmodeEps implements IConcSyn.IOptionalFLOWMODE{

	public OptionalFlowmodeEps() {
	}

	@Override
	public Token toAbs() {
		//Default value for Flowmode is IN
		return new Token(Terminals.FLOWMODE, Operators.IN);
	}
}
