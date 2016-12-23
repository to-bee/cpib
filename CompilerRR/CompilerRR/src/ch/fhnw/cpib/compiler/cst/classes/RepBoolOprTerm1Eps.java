package ch.fhnw.cpib.compiler.cst.classes;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IBOOLOPRterm1;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;

public class RepBoolOprTerm1Eps implements IConcSyn.IRepBOOLOPRterm1 {

	public RepBoolOprTerm1Eps() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<IBOOLOPRterm1> toAbs() {
		return new LinkedList<IAbsSyn.IBOOLOPRterm1>();
	}
}
