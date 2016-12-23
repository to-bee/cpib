package ch.fhnw.cpib.compiler.ast.classes;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ICommand;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression;

public class OutputCmd implements ICommand{
	IExpression expression;

	public OutputCmd(IExpression expression) {
		super();
		this.expression = expression;
	}
	
	
}
