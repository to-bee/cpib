package ch.fhnw.cpib.compiler.ast.classes;

import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ICommand;

public class CondCmd implements ICommand {
	IExpression expression;
	List<ICommand> commandsThen;
	List<ICommand> commandsElse;

	public CondCmd(IExpression expression, List<ICommand> commandsThen,
			List<ICommand> commandsElse) {
		super();
		this.expression = expression;
		this.commandsThen = commandsThen;
		this.commandsElse = commandsElse;
	}

}
