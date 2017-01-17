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
import ch.fhnw.cpib.compiler.vm.IInstructions;
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
		
		int loc = i;
		
		int[] condJumpFromPositions = new int[cases.size()];
		int[] condJumpToPositions = new int[cases.size()];
		
		int[] uncondJumpFromPositions = new int[cases.size()];
		int endJumpPoint = -1;
		
		int j = 0; // current index
		for(ICase currentCase : cases){
			loc = expression.code(loc);
			carr.put(loc++, new IInstructions.LoadImInt(((LiteralToken)currentCase.getToken()).getValue()));
			carr.put(loc++, new IInstructions.EqInt());
			condJumpFromPositions[j] = loc++;
			loc = currentCase.code(loc);
			uncondJumpFromPositions[j] = loc++;
			condJumpToPositions[j] = loc;
			j++;
		}
		// default case
		for(ICommand cmd : defaultCommands)
			loc = cmd.code(loc);
		
		endJumpPoint = loc;
		
		// create Jumps
		for(int k = 0; k < condJumpFromPositions.length; k++){
			carr.put(condJumpFromPositions[k], new IInstructions.CondJump(condJumpToPositions[k]));
		}
		for(int location : uncondJumpFromPositions){
			carr.put(location, new IInstructions.UncondJump(endJumpPoint));
		}
		
		return endJumpPoint;
	}

	@Override
	public void print(String prefix) {
		expression.print(prefix);
		for(ICase c : cases){
			c.print(prefix+"-");
		}
		for(ICommand c : defaultCommands){
			c.print(prefix+"-");
		}
	}

}
