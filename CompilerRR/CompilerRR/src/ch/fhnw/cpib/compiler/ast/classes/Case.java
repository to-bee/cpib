package ch.fhnw.cpib.compiler.ast.classes;

import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ICase;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ICommand;
import ch.fhnw.cpib.compiler.scanner.Token;

public class Case implements ICase {
	Token literal;
	List<ICommand> commands;

	public Case(Token literal, List<ICommand> commands) {
		super();
		this.literal = literal;
		this.commands = commands;
	}

}
