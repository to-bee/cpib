package ch.fhnw.cpib.compiler.ast.classes;

import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IADDOPRterm3;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ITerm2;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ITerm3;

public class Term2 implements ITerm2 {
	ITerm3 term3;
	List<IADDOPRterm3> addOprTerm3List;

	public Term2(ITerm3 term3, List<IADDOPRterm3> addOprTerm3List) {
		super();
		this.term3 = term3;
		this.addOprTerm3List = addOprTerm3List;
	}

}
