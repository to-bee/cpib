package ch.fhnw.cpib.compiler.cst.classes;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;

public class RepeatingOptionalStorageDeclarationsEps implements IConcSyn.IRepeatingOptionalStorageDeclarations{

	public RepeatingOptionalStorageDeclarationsEps() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IStorageDeclaration> toAbs() {
		return new LinkedList<IAbsSyn.IStorageDeclaration>();
	}
}
