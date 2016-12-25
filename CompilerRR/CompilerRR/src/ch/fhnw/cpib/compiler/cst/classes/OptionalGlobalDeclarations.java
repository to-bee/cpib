package ch.fhnw.cpib.compiler.cst.classes;

import java.util.List;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.scanner.Token;

public class OptionalGlobalDeclarations implements IConcSyn.IOptionalGlobalDeclarations {

	Token global;
	IConcSyn.IDeclarations decls;

	public OptionalGlobalDeclarations(Token global, IDeclarations decls) {
		super();
		this.global = global;
		this.decls = decls;
	}

	@Override
	public List<ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IDeclaration> toAbs() {
		return decls.toAbs();
	}
	
	
	
}
