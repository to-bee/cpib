package ch.fhnw.cpib.compiler.ast.classes;

import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression;

public class Expression implements IExpression {
	ITerm1 term1;
	List<IBOOLOPRterm1> boolOprTerm1List;

	public Expression(ITerm1 term1, List<IBOOLOPRterm1> boolOprTerm1List) {
		super();
		this.term1 = term1;
		this.boolOprTerm1List = boolOprTerm1List;
	}

}
