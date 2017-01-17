package ch.fhnw.cpib.compiler.ast.classes;

import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ICommand;
import ch.fhnw.cpib.compiler.context.CompilerE;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.operators.Type;
import ch.fhnw.cpib.compiler.vm.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.compiler.vm.IInstructions.*;
import ch.fhnw.cpib.compiler.vm.ICodeArray.CodeTooSmallError;

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

	@Override
	public int code(int i) throws CodeTooSmallError {
		int loc1 = expression.code(i);
		int loc2 = loc1 + 1;
		
		int loc3 = loc2;
		for (ICommand iCommand : commands) {
			loc3 = iCommand.code(loc3);
		}
		int loc4 = loc3+1;
		CompilerE.COMPILER.getCodeArray().put(loc1, new CondJump(loc4));
		CompilerE.COMPILER.getCodeArray().put(loc3, new UncondJump(i));
		
		return loc4;
	}

	@Override
	public void print(String prefix) {
		expression.print(prefix);
		for(ICommand c : commands){
			c.print(prefix+ "-");
		}
	}

}
