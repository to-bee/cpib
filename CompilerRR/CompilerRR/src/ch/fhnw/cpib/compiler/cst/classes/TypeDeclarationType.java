package ch.fhnw.cpib.compiler.cst.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.scanner.Token;

public class TypeDeclarationType implements IConcSyn.ITypeDeclaration{
 Token type;

public TypeDeclarationType(Token type) {
	super();
	this.type = type;
}

@Override
public ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ITypeDeclaration toAbs() {
	return new ch.fhnw.cpib.compiler.ast.classes.TypeDeclarationType(type);
}
 
 
}
