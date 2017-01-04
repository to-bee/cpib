package ch.fhnw.cpib.compiler.ast.classes;

import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ICommand;
import ch.fhnw.cpib.compiler.context.CompilerE;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.operators.Type;
import ch.fhnw.cpib.compiler.vm.ICodeArray;
import ch.fhnw.cpib.compiler.vm.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.compiler.vm.IInstructions.*;

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

	@Override
	public int code(int i) throws CodeTooSmallError {
		ICodeArray codeArr = CompilerE.COMPILER.getCodeArray();
	    int loc = i;
	    
	    //vm.DebugInfo(loc++, this.getClass().getSimpleName(), this.getToken());
	   
	    loc = this.expression.code(loc);
	    int locJump1 = loc++;
	    //vm.DebugInfo(loc++, "TRUE -> THEN", this.thenCmd.getToken());
	    
	    int loc2 = loc;
	    for (ICommand iCommand : commandsThen) {
	    	 loc2 = iCommand.code(loc2);
		}
	 
	    int locJump2 = loc2++;
	    int locElse = loc2;
	    
	    //vm.DebugInfo(loc++, "FALSE -> ELSE", this.elseCmd.getToken());
	    
	    for (ICommand iCommand : commandsElse) {
			loc2 = iCommand.code(loc2);
		}

	    codeArr.put(locJump1, new CondJump(locElse));
	    codeArr.put(locJump2, new UncondJump(loc));

	    return loc2;
	}

}
