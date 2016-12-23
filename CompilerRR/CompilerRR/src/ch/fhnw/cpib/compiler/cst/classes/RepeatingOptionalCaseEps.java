package ch.fhnw.cpib.compiler.cst.classes;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ICase;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;

public class RepeatingOptionalCaseEps implements IConcSyn.IRepeatingOptionalCase{

	public RepeatingOptionalCaseEps() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<ICase> toAbs() {
		return new LinkedList<IAbsSyn.ICase>();
	}
}
