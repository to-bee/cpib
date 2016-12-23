package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.scanner.Token;

public class OptionalGlobalDeclarations implements IConcSyn.IOptionalGlobalDeclarations {

	Token global;
	IConcSyn.IDeclarations decls;

	public OptionalGlobalDeclarations(Token global) {
		super();
		this.global = global;
	}
	
	
	
}
