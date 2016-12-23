package ch.fhnw.cpib.compiler.ast.classes;

import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ICommand;

public class CpsCmd implements ICommand{
	List<ICommand> commands;

	public CpsCmd(List<ICommand> commands) {
		super();
		this.commands = commands;
	}
}
