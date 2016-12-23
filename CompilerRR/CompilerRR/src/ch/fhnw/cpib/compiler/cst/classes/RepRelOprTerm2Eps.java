package ch.fhnw.cpib.compiler.cst.classes;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IRELOPRterm2;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;

public class RepRelOprTerm2Eps implements IConcSyn.IRepRELOPRterm2{

	public RepRelOprTerm2Eps() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<IRELOPRterm2> toAbs() {
		return new LinkedList<IAbsSyn.IRELOPRterm2>();
	}
}
