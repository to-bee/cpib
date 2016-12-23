package ch.fhnw.cpib.compiler.cst.classes;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.scanner.Token;

public class OptionalLocalStorageDeclaration implements IConcSyn.IOptionalLocalStorageDeclarations{

	Token local;
	IConcSyn.IStorageDeclaration stoDecl;
	IConcSyn.IRepeatingOptionalStorageDeclarations repOptDecl;
	public OptionalLocalStorageDeclaration(Token local, IStorageDeclaration stoDecl,
			IRepeatingOptionalStorageDeclarations repOptStoDecl) {
		super();
		this.local = local;
		this.stoDecl = stoDecl;
		this.repOptDecl = repOptStoDecl;
	}
	
	@Override
	public List<ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IStorageDeclaration> toAbs() {
		List<ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IStorageDeclaration>
		declarations = new LinkedList<IAbsSyn.IStorageDeclaration>();
		declarations.add(stoDecl.toAbs());
		declarations.addAll(repOptDecl.toAbs());
		return declarations;
	}
	
	
}
