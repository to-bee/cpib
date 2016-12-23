package ch.fhnw.cpib.compiler.cst.classes;

import java.util.List;

import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.scanner.Token;

public class Expressionlist implements IConcSyn.IExpressionList {
 
	Token lpar;
	IConcSyn.IOptionalExpressions optExpr;
	Token rpar;
	public Expressionlist(Token lpar, IOptionalExpressions optExpr, Token rpar) {
		super();
		this.lpar = lpar;
		this.optExpr = optExpr;
		this.rpar = rpar;
	}
	
	@Override
	public List<ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression> toAbs() {
		return optExpr.toAbs();
	}
	
	
}
