package ch.fhnw.cpib.compiler.cst.classes;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IADDOPRterm3;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;

public class RepAddOprTerm3Eps implements IConcSyn.IRepADDOPRterm3{

	public RepAddOprTerm3Eps() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<IADDOPRterm3> toAbs() {
		return new LinkedList<IAbsSyn.IADDOPRterm3>();
	}
}
