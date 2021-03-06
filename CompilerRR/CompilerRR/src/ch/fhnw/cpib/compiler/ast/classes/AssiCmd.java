package ch.fhnw.cpib.compiler.ast.classes;


import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.ICommand;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression;
import ch.fhnw.cpib.compiler.context.CompilerE;
import ch.fhnw.cpib.compiler.context.Variable;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.operators.ChangeMode;
import ch.fhnw.cpib.compiler.scanner.enums.operators.Type;
import ch.fhnw.cpib.compiler.scanner.tokens.LiteralToken;
import ch.fhnw.cpib.compiler.vm.ICodeArray;
import ch.fhnw.cpib.compiler.vm.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.compiler.vm.IInstructions.*;

public class AssiCmd implements ICommand {
	IExpression expressionLeft;
	IExpression expressionRight;
	
	public AssiCmd(IExpression expressionLeft, IExpression expressionRight) {
		super();
		this.expressionLeft = expressionLeft;
		this.expressionRight = expressionRight;
	}
	
	
	@Override
	public void check() throws RuntimeException {
		Token token = expressionLeft.getToken();
		
		if (!(expressionLeft instanceof StoreExpression)) throw new RuntimeException("Lefthandside not a Variable " + token.toString());
		
		StoreExpression storeExpr = (StoreExpression) expressionLeft;
	    storeExpr.setWrite(true);
	    
	    Variable var1 = CompilerE.COMPILER.getCurrentContext().getVariable(storeExpr.getToken());
        if (var1 == null)
          throw new RuntimeException("Varibale does not exist." + token.toString());
        if (var1.getChangeMode() == ChangeMode.CONST && var1.isInitialized())
          throw new RuntimeException("Cannot assign value to CONST."+ token.toString());
	   
        this.expressionLeft.check();
        this.expressionRight.check();
	    Type tL = expressionLeft.getType();
	    Type tR = expressionRight.getType();
	    
	    if (tL == Type.BOOL && tR == Type.INT32) {
			if ( ((LiteralToken)expressionRight.getToken()).getValue() > 1 ||  ((LiteralToken)expressionRight.getToken()).getValue() < 0) {
				throw new RuntimeException("Invalid Assignment: Left: " + tL + " Right: " +tR );
			}
		}
	    
	}


	@Override
	public Token getToken() {
		return expressionLeft.getToken();
	}


	@Override
	public int code(int i) throws CodeTooSmallError {
		ICodeArray codeArr = CompilerE.COMPILER.getCodeArray();
	    
	    int loc = i;
	    
	    loc = this.expressionLeft.code(loc);
	    loc = this.expressionRight.code(loc);
	    codeArr.put(loc++, new Store());
	   
	    return loc;
	}


	@Override
	public void print(String prefix) {
		expressionLeft.print(prefix);
		expressionRight.print(prefix);
	}
	
}
