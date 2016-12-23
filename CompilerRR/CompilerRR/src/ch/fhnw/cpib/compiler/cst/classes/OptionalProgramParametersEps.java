package ch.fhnw.cpib.compiler.cst.classes;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IProgramParameter;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;

public class OptionalProgramParametersEps implements IConcSyn.IOptionalProgramParameters{

	public OptionalProgramParametersEps() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<IProgramParameter> toAbs() {
		return new LinkedList<IAbsSyn.IProgramParameter>();
	}
}
