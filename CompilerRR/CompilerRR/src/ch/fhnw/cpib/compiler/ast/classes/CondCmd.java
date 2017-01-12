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

		for (ICommand iCommand : commandsThen) {
			iCommand.check();
		}
		for (ICommand iCommand : commandsElse) {
			iCommand.check();
		}
	}

	@Override
	public Token getToken() {
		return expression.getToken();
	}

	@Override
	public int code(int i) throws CodeTooSmallError {
		ICodeArray codeArr = CompilerE.COMPILER.getCodeArray();
	    int loc = i;
	    
	    loc = this.expression.code(loc);
	    int locJump1 = loc++;
	    
	    for (ICommand iCommand : commandsThen) {
	    	 loc = iCommand.code(loc);
		}
	 
	    int locJump2 = loc++;
	    int locElse = loc;
	    
	    
	    for (ICommand iCommand : commandsElse) {
			loc = iCommand.code(loc);
		}

	    codeArr.put(locJump1, new CondJump(locElse));
	    codeArr.put(locJump2, new UncondJump(loc));

	    
	    System.out.println("[ "+this.getClass().getSimpleName()+" ]");
	    for(int ii = i; ii < loc; ii++){
	    	if(CompilerE.COMPILER.getCodeArray().get(ii) != null)
	    		System.out.println(CompilerE.COMPILER.getCodeArray().get(ii).toString());
	    	else System.out.println("null <--------------------------");
	    }
	    return loc;
	}

}
