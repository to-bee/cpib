package ch.fhnw.cpib.compiler.ast.classes;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression;
import ch.fhnw.cpib.compiler.context.CompilerE;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Operators;
import ch.fhnw.cpib.compiler.scanner.enums.Terminals;
import ch.fhnw.cpib.compiler.scanner.enums.operators.Type;
import ch.fhnw.cpib.compiler.vm.ICodeArray;
import ch.fhnw.cpib.compiler.vm.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.compiler.vm.IInstructions.*;

public class MonadicExpression implements IExpression{

	Token operator;
	IExpression expr;
	Type type = null;
	
	public MonadicExpression(Token operator, IExpression expr) {
		super();
		this.operator = operator;
		this.expr = expr;
	}

	@Override
	public void check() {
		this.expr.check();
	}

	@Override
	public Token getToken() {
		return this.operator;
	}

	@Override
	public Type getType() {
		if (this.operator.getTerminal() == Terminals.NOTOPER) this.type = Type.BOOL;
	    else this.type = Type.INT32; // ADDOPR
		return type;
	}

	@Override
	public int code(int i) throws CodeTooSmallError {
		System.out.println("+++ " + this.getClass().getSimpleName() + " : wird nicht geprinted. +++");
		
		ICodeArray codeArr = CompilerE.COMPILER.getCodeArray();
	    int loc = i;
	    //vm.DebugInfo(loc++, this.getClass().getSimpleName(), this.getToken());
	    loc = this.expr.code(loc);
	    Terminals terminal = this.operator.getTerminal();
	    if (terminal == Terminals.NOTOPER) {
	    	codeArr.put(loc++, new LoadImInt(0));
	    	codeArr.put(loc++, new EqInt());
	    	return loc;
	    } else if (terminal == Terminals.ADDOPR) {
	      if (this.operator.getOperator() == Operators.MINUS) {
	    	codeArr.put(loc++, new NegInt());
	        return loc;
	      } else if (this.operator.getOperator() == Operators.PLUS) {
	        return loc;
	      }
	    }
	    throw new RuntimeException("MonadicExpr: Unknown operator.");
	}

}
