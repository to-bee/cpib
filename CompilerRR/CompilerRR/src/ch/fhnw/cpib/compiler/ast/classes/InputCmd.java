package ch.fhnw.cpib.compiler.ast.classes;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ICommand;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression;
import ch.fhnw.cpib.compiler.scanner.Token;

public class InputCmd implements ICommand{
	IExpression expression;

	public InputCmd(IExpression expression) {
		super();
		this.expression = expression;
	}

	@Override
	public void check() {
		expression.check();
		
		if (!(this.expression instanceof StoreExpression))
		      throw new RuntimeException("Cannot store input into given Expression " +
		          this.expression.getToken().toString());

		((StoreExpression) this.expression).setWrite(true);
		
	}

	@Override
	public Token getToken() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
