package ch.fhnw.cpib.compiler.cst.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Operators;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;

public class OptionalMechmodeEps implements IConcSyn.IOptionalMECHMODE{

	public OptionalMechmodeEps() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Token toAbs() {
		//Default param is Copy
		return new Token(Terminals.MECHMODE, Operators.COPY);
	}
}
