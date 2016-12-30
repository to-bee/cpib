package ch.fhnw.cpib.compiler.ast.classes;

import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ICommand;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.operators.Type;

public class WhileCmd implements ICommand {
	IExpression expression;
	List<ICommand> commands;

	public WhileCmd(IExpression expression, List<ICommand> commands) {
		super();
		this.expression = expression;
		this.commands = commands;
	}

	@Override
	public void check() {
		this.expression.check();
	    if(this.expression.getType() != Type.BOOL)
	      throw new RuntimeException("The condition of this while is not of type BOOL");
	    
	    for (ICommand iCommand : commands) {
			iCommand.check();
		}
		
	}

	@Override
	public Token getToken() {
		return this.expression.getToken();
	}

}
