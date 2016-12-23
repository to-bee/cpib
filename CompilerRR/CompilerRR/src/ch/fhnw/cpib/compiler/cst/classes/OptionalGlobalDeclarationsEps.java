package ch.fhnw.cpib.compiler.cst.classes;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;

public class OptionalGlobalDeclarationsEps implements IConcSyn.IOptionalGlobalDeclarations {

	public OptionalGlobalDeclarationsEps() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IDeclaration> toAbs() {
		return new LinkedList<IAbsSyn.IDeclaration>();
	}
}
