package ch.fhnw.cpib.compiler.ast.classes;

import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ICommand;

public class WhileCmd implements ICommand {
	IExpression expression;
	List<ICommand> commands;

	public WhileCmd(IExpression expression, List<ICommand> commands) {
		super();
		this.expression = expression;
		this.commands = commands;
	}

}
