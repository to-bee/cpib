package ch.fhnw.cpib.compiler.classes;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.scanner.Token;

public class TypeDeclarationType implements IConcSyn.ITypeDeclaration{
 Token type;

public TypeDeclarationType(Token type) {
	super();
	this.type = type;
}
 
 
}
