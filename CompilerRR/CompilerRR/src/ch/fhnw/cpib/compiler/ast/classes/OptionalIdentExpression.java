package ch.fhnw.cpib.compiler.ast.classes;

import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IOptionalIdent;

public class OptionalIdentExpression implements IOptionalIdent{
	
	List<IExpression> expressions;

	public OptionalIdentExpression(List<IExpression> expressions) {
		super();
		this.expressions = expressions;
	}

	@Override
	public void check() {
		// TODO Auto-generated method stub
		
	}


	
}
