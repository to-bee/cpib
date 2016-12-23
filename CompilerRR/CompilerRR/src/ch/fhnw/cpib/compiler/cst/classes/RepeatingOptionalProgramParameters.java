package ch.fhnw.cpib.compiler.cst.classes;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.ast.classes.ProgramParameter;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn;
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
	
	@Override
	public List<ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IProgramParameter> toAbs() {
		List<ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IProgramParameter>
		params = new LinkedList<IAbsSyn.IProgramParameter>();
		params.add(new ProgramParameter(flowMode.toAbs(), changeMode.toAbs(), typedIdent.toAbs()));
		params.addAll(repOptProPar.toAbs());
		return params;
	}
	
	
	
}
