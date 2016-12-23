package ch.fhnw.cpib.compiler.cst.classes;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.scanner.Token;

public class OptionalGlobalInitsEps implements IConcSyn.IOptionalGlobalInits{

	public OptionalGlobalInitsEps() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Token> toAbs() {
		return new LinkedList<Token>();
	}
}
