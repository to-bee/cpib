package ch.fhnw.cpib.compiler.cst.classes;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ICommand;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;

public class RepeatingOptionalCmdsEps implements IConcSyn.IRepeatingOptionalCmds{

	public RepeatingOptionalCmdsEps() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<ICommand> toAbs() {
		return new LinkedList<IAbsSyn.ICommand>();
	}
}
