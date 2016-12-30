package ch.fhnw.cpib.compiler.ast.classes;

import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ICommand;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.operators.Type;

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

	@Override
	public void check() {
		expression.check();
		if (expression.getType() != Type.BOOL) throw new RuntimeException("Expression != BOOL");
		//TODO: Really a list?
		for (ICommand iCommand : commandsThen) {
			iCommand.check();
		}
		for (ICommand iCommand : commandsElse) {
			iCommand.check();
		}
	}

	@Override
	public Token getToken() {
		// TODO Auto-generated method stub
		return null;
	}

}
