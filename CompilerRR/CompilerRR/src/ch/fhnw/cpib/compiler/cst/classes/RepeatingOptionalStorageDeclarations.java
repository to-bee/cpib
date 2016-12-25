package ch.fhnw.cpib.compiler.cst.classes;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.scanner.Token;

public class RepeatingOptionalStorageDeclarations implements IConcSyn.IRepeatingOptionalStorageDeclarations {

	Token semicolon;
	IConcSyn.IStorageDeclaration stoDecl;
	IConcSyn.IRepeatingOptionalStorageDeclarations repOptStoDecl;
	public RepeatingOptionalStorageDeclarations(Token semicolon, IStorageDeclaration stoDecl,
			IRepeatingOptionalStorageDeclarations repOptStoDecl) {
		super();
		this.semicolon = semicolon;
		this.stoDecl = stoDecl;
		this.repOptStoDecl = repOptStoDecl;
	}
	
	@Override
	public List<ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IDeclaration> toAbs() {
		List<ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IDeclaration> decls = new LinkedList<>();
		decls.add(stoDecl.toAbs());
		decls.addAll(repOptStoDecl.toAbs());
		return decls;
	}
	
	
}
