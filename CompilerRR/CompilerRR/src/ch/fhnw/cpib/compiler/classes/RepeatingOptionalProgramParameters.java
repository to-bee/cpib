package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IOptionalCHANGEMODE;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IOptionalFLOWMODE;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IRepeatingOptionalProgramParameters;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.ITypedIdent;
import ch.fhnw.cpib.compiler.scanner.Token;

public class RepeatingOptionalProgramParameters implements IConcSyn.IRepeatingOptionalProgramParameters{

	Token comma;
	IConcSyn.IOptionalFLOWMODE flowMode;
	IConcSyn.IOptionalCHANGEMODE changeMode;
	IConcSyn.ITypedIdent typedIdent;
	IConcSyn.IRepeatingOptionalProgramParameters repOptProPar;
	public RepeatingOptionalProgramParameters(Token comma, IOptionalFLOWMODE flowMode, IOptionalCHANGEMODE changeMode,
			ITypedIdent typedIdent, IRepeatingOptionalProgramParameters repOptProPar) {
		super();
		this.comma = comma;
		this.flowMode = flowMode;
		this.changeMode = changeMode;
		this.typedIdent = typedIdent;
		this.repOptProPar = repOptProPar;
	}
	
	
	
}
