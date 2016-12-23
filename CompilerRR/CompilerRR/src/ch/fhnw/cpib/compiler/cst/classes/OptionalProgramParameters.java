package ch.fhnw.cpib.compiler.cst.classes;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.ast.classes.ProgramParameter;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IProgramParameter;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;

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
	@Override
	public List<IProgramParameter> toAbs() {
		List<IProgramParameter> params = new LinkedList<IAbsSyn.IProgramParameter>();
		params.add(new ProgramParameter(flowMode.toAbs(), changeMode.toAbs(), typedIdent.toAbs()));
		params.addAll(repOptProPar.toAbs());
		return params;
	}
	
	
}
