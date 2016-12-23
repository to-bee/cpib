package ch.fhnw.cpib.compiler.ast.classes;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ICommand;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression;

public class InputCmd implements ICommand{
	IExpression expression;

	public InputCmd(IExpression expression) {
		super();
		this.expression = expression;
	}
	
	
}
