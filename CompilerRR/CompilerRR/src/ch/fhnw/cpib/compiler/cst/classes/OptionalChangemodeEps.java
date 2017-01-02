package ch.fhnw.cpib.compiler.cst.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Operators;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class OptionalChangemodeEps implements IConcSyn.IOptionalCHANGEMODE{

	public OptionalChangemodeEps() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Token toAbs() {
		//Default value for Changemode is VAR
		return new Token(Terminals.CHANGEMODE, Operators.VAR);
	}

}
