package ch.fhnw.cpib.compiler.cst.classes;

import java.util.LinkedList;
import java.util.List;

import ch.fhnw.cpib.compiler.ast.classes.DyadicExpression;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn;
import ch.fhnw.cpib.compiler.cst.interfaces.IConcSyn;
import ch.fhnw.cpib.compiler.scanner.Token;

public class RepAddOprTerm3 implements IConcSyn.IRepADDOPRterm3 {

	Token addOpr;
	IConcSyn.ITerm3 t3;
	IConcSyn.IRepADDOPRterm3 repAddOprT3;
	public RepAddOprTerm3(Token addOpr, ITerm3 t3, IRepADDOPRterm3 repAddOprT3) {
		super();
		this.addOpr = addOpr;
		this.t3 = t3;
		this.repAddOprT3 = repAddOprT3;
	}
	
	@Override
	public ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression toAbs(ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IExpression dyadicExpr) {
		return repAddOprT3.toAbs(new DyadicExpression(this.addOpr, dyadicExpr, this.t3.toAbs()));
	}
	
	
}
