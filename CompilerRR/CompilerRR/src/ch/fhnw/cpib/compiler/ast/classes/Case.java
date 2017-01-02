package ch.fhnw.cpib.compiler.ast.classes;

import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ICase;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ICommand;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.tokens.LiteralToken;
import ch.fhnw.cpib.compiler.vm.ICodeArray.CodeTooSmallError;

public class Case implements ICase {
	Token literal;
	List<ICommand> commands;

	public Case(Token literal, List<ICommand> commands) {
		super();
		this.literal = literal;
		this.commands = commands;
	}

	@Override
	public void check() {
		if (literal instanceof LiteralToken) {
			int val = ((LiteralToken)literal).getValue();
			//TODO: Check if value ok
		} else {
			throw new RuntimeException("Casetoken not Literal " + literal.toString());
		}
		
		
		for (ICommand iCommand : commands) {
			iCommand.check();
		}
		
	}
	
	@Override
	public Token getToken(){
		return literal;
	}

	@Override
	public int code(int i) throws CodeTooSmallError {
		
		int loc2 = i;
		for (ICommand iCommand : commands) {
			loc2 = iCommand.code(loc2);
		}
		return loc2;
	}

}
