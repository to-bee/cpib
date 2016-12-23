package ch.fhnw.cpib.compiler.ast.classes;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IBOOLOPRterm1;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ITerm1;
import ch.fhnw.cpib.compiler.scanner.Token;

public class BoolOprTerm1 implements IBOOLOPRterm1 {
	Token boolOpr;
	ITerm1 term1;

	public BoolOprTerm1(Token boolOpr, ITerm1 term1) {
		super();
		this.boolOpr = boolOpr;
		this.term1 = term1;
	}
}
