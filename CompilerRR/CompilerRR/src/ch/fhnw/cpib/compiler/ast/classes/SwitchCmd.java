package ch.fhnw.cpib.compiler.ast.classes;

import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ICase;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ICommand;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression;

public class SwitchCmd implements ICommand {
	IExpression expression;
	List<ICase> cases;
	List<ICommand> defaultCommands;

	public SwitchCmd(IExpression expression, List<ICase> cases,
			List<ICommand> defaultCommands) {
		super();
		this.expression = expression;
		this.cases = cases;
		this.defaultCommands = defaultCommands;
	}

}