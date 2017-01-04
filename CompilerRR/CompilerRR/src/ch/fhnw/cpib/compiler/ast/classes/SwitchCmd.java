package ch.fhnw.cpib.compiler.ast.classes;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ICommand;
import ch.fhnw.cpib.compiler.context.CompilerE;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.operators.Type;
import ch.fhnw.cpib.compiler.scanner.tokens.LiteralToken;
import ch.fhnw.cpib.compiler.vm.ICodeArray;
import ch.fhnw.cpib.compiler.vm.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.compiler.vm.IInstructions.LoadImInt;

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

	@Override
	public void check() {
		//First check expression, so type is not null
		expression.check();
		
		System.out.println(expression.getClass());
		System.out.println(expression.getType());
		
		if (expression.getType() != Type.INT32) {
			throw new RuntimeException("Wrong Type of Switchexpression");
		}
		for (ICase iCase : cases) {
			iCase.check();
		}
		for (ICommand iCommand : defaultCommands) {
			iCommand.check();
		}
		
		if (cases.size() > 1) {
			List<Integer> checkerList = new LinkedList<>();
			for (ICase iCase : cases) {
				if (!(iCase.getToken() instanceof LiteralToken) || checkerList.contains( ((LiteralToken)iCase.getToken()).getValue() )) {
					throw new RuntimeException("Duplicate Cases");
				} else {
					checkerList.add( ((LiteralToken)iCase.getToken()).getValue() );
				}
			}
		}
		
		
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public Token getToken() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int code(int i) throws CodeTooSmallError {
		ICodeArray carr = CompilerE.COMPILER.getCodeArray();
		
		int loc1 = expression.code(i);
		int value = ((LoadImInt)carr.get(i)).getValue();
		
		int loc2 = loc1;
		boolean found = false;
		for (ICase iCase : cases) {
			if (((LiteralToken)iCase.getToken()).getValue() == value) {
				loc2 = iCase.code(loc2);
				found = true;
				break;
			}
		}
		if (!found) {
			for (ICommand iCommand : defaultCommands) {
				loc2 = iCommand.code(loc2);
			}
			
		}
		return loc2;
	}

}
