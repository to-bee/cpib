package ch.fhnw.cpib.compiler.ast.classes;

import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IRELOPRterm2;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ITerm1;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ITerm2;

public class Term1 implements ITerm1{
	ITerm2 term2;
	List<IRELOPRterm2> relOprTerm2List;

	public Term1(ITerm2 term2, List<IRELOPRterm2> relOprTerm2List) {
		super();
		this.term2 = term2;
		this.relOprTerm2List = relOprTerm2List;
	}

}
