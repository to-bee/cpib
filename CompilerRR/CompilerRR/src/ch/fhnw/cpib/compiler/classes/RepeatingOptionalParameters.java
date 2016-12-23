package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IParameter;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IRepeatingOptionalParameters;
import ch.fhnw.cpib.compiler.scanner.Token;

public class RepeatingOptionalParameters implements IConcSyn.IRepeatingOptionalParameters {

	Token comma;
	IConcSyn.IParameter param;
	IConcSyn.IRepeatingOptionalParameters repOptParams;
	public RepeatingOptionalParameters(Token comma, IParameter param, IRepeatingOptionalParameters repOptParams) {
		super();
		this.comma = comma;
		this.param = param;
		this.repOptParams = repOptParams;
	}
	
	
}
