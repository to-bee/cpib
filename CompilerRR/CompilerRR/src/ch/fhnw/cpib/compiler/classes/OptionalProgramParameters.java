package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IOptionalCHANGEMODE;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IOptionalFLOWMODE;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IRepeatingOptionalProgramParameters;
import ch.fhnw.cpib.compiler.scanner.Token;

public class OptionalProgramParameters implements IConcSyn.IOptionalProgramParameters {

	IConcSyn.IOptionalFLOWMODE flowMode;
	IConcSyn.IOptionalCHANGEMODE changeMode;
	IConcSyn.ITypedIdent typedIdent;
	IConcSyn.IRepeatingOptionalProgramParameters repOptProPar;
	public OptionalProgramParameters(IOptionalFLOWMODE flowMode, IOptionalCHANGEMODE changeMode, IConcSyn.ITypedIdent typedIdent,
			IRepeatingOptionalProgramParameters repOptProPar) {
		super();
		this.flowMode = flowMode;
		this.changeMode = changeMode;
		this.typedIdent = typedIdent;
		this.repOptProPar = repOptProPar;
	}
	
	
}
