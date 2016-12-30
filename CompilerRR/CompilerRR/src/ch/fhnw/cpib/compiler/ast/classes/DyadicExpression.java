package ch.fhnw.cpib.compiler.ast.classes;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.Operators;
import ch.fhnw.cpib.compiler.scanner.enums.operators.RelOpr;
import ch.fhnw.cpib.compiler.scanner.enums.operators.Type;


public class DyadicExpression implements IExpression {
	
	private Token operator;
	private IExpression lExpr;
	private IExpression rExpr;
	private Type  type = null;

	public DyadicExpression(Token operator, IExpression lExpr, IExpression rExpr) {
		super();
		this.operator = operator;
		this.lExpr = lExpr;
		this.rExpr = rExpr;
	}
	
	@Override
	public void check() {
		this.lExpr.check();

		//TODO: All RELOPR must be checked
	    final Operators op = this.operator.getOperator();
	    if (op == Operators.EQ ||
	    	op == Operators.LT ||
	    	op == Operators.GT  		
	    		) this.type = Type.BOOL;
	    else this.type = this.lExpr.getType();

	    this.rExpr.check();

	    if (this.lExpr.getType() != this.lExpr.getType())
	      throw new RuntimeException("Two different types in dyadic expression.");
	}
	

	@Override
	public Token getToken() {
		return operator;
	}

	@Override
	public Type getType() {
	  return this.type;
	}
	  
}
