package ch.fhnw.cpib.compiler.ast.classes;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IADDOPRterm3;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ITerm3;
import ch.fhnw.cpib.compiler.scanner.Token;

public class AddOprTerm3 implements IADDOPRterm3{
	Token addOpr;
	ITerm3 term3;
	public AddOprTerm3(Token addOpr, ITerm3 term3) {
		super();
		this.addOpr = addOpr;
		this.term3 = term3;
	}

	
	
}
