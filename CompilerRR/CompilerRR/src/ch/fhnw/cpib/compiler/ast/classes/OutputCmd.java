package ch.fhnw.cpib.compiler.ast.classes;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ICommand;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression;
import ch.fhnw.cpib.compiler.scanner.Token;

public class OutputCmd implements ICommand{
	
	IExpression expression;

	public OutputCmd(IExpression expression) {
		super();
		this.expression = expression;
	}

	@Override
	public void check() {
		this.expression.check();		
	}
	
	 @Override
	 public Token getToken() {
	    return this.expression.getToken();
	 }
	
	
}
