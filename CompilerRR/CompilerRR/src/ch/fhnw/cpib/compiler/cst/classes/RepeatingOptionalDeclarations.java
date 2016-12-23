package ch.fhnw.cpib.compiler.cst.classes;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.scanner.Token;

public class RepeatingOptionalDeclarations implements IConcSyn.IRepeatingOptionalDeclarations{

	Token semicolon;
	IConcSyn.IDeclaration stoDecl;
	IConcSyn.IRepeatingOptionalDeclarations repOptDecl;
	public RepeatingOptionalDeclarations(Token semicolon, IDeclaration decl,
			IRepeatingOptionalDeclarations repOptDecl) {
		super();
		this.semicolon = semicolon;
		this.stoDecl = decl;
		this.repOptDecl = repOptDecl;
	}
	
	@Override
	public List<ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IDeclaration> toAbs() {
		List<ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IDeclaration> 
		declarations = new LinkedList<IAbsSyn.IDeclaration>();
		declarations.add(stoDecl.toAbs());
		declarations.addAll(repOptDecl.toAbs());
		return declarations;
	}
	
	
}
