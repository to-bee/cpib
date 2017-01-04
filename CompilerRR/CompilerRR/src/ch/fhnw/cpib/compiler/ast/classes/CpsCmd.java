package ch.fhnw.cpib.compiler.ast.classes;

import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ICommand;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.vm.ICodeArray.CodeTooSmallError;

public class CpsCmd implements ICommand{
	List<ICommand> commands;

	public CpsCmd(List<ICommand> commands) {
		super();
		this.commands = commands;
	}

	@Override
	public void check() {
		//TODO: Really?
		for (ICommand iCommand : commands) {
			iCommand.check();
		}
		
	}

	@Override
	public Token getToken() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int code(int i) throws CodeTooSmallError {
		int loc = i;
		
		for (ICommand iCommand : commands) {
			loc = iCommand.code(loc);
		}
		return loc;
	}
}
