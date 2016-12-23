package ch.fhnw.cpib.compiler.ast.classes;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IRELOPRterm2;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ITerm2;
import ch.fhnw.cpib.compiler.scanner.Token;

public class RelOprTerm2 implements IRELOPRterm2{
	Token relOpr;
	ITerm2 term2;
	public RelOprTerm2(Token relOpr, ITerm2 term2) {
		super();
		this.relOpr = relOpr;
		this.term2 = term2;
	}

}
