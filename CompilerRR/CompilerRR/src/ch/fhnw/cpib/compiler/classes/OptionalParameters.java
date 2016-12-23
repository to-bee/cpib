package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IParameter;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IRepeatingOptionalParameters;

public class OptionalParameters implements IConcSyn.IOptionalParameters {

	IConcSyn.IParameter param;
	IConcSyn.IRepeatingOptionalParameters repOptPar;
	public OptionalParameters(IParameter param, IRepeatingOptionalParameters repOptPar) {
		super();
		this.param = param;
		this.repOptPar = repOptPar;
	};
	
	
}
