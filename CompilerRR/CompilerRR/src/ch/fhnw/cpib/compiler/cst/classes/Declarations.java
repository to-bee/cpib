package ch.fhnw.cpib.compiler.cst.classes;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IDeclaration;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn.IRepeatingOptionalDeclarations;

public class Declarations implements IConcSyn.IDeclarations{

	IConcSyn.IDeclaration decl;
	IConcSyn.IRepeatingOptionalDeclarations repOptDecl;
	public Declarations(IDeclaration decl, IRepeatingOptionalDeclarations repOptDecl) {
		super();
		this.decl = decl;
		this.repOptDecl = repOptDecl;
	}
	
	@Override
	public List<ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IDeclaration> toAbs() {
		List<ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IDeclaration> 
		declarations = new LinkedList<>();
		declarations.add(decl.toAbs());
		declarations.addAll(repOptDecl.toAbs());
		return declarations;
	}
	
	
	
}
