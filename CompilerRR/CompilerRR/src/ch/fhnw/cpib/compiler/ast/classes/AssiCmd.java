package ch.fhnw.cpib.compiler.ast.classes;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ICommand;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression;

public class AssiCmd implements ICommand{
	IExpression expressionLeft;
	IExpression expressionRight;
	
	public AssiCmd(IExpression expressionLeft, IExpression expressionRight) {
		super();
		this.expressionLeft = expressionLeft;
		this.expressionRight = expressionRight;
	}
	
	
}
